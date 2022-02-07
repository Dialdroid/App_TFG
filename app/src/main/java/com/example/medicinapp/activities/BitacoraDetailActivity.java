package com.example.medicinapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicinapp.R;
import com.example.medicinapp.fragments.BinnacleFragment;
import com.example.medicinapp.models.SliderItem;
import com.example.medicinapp.providers.AuthProvider;
import com.example.medicinapp.providers.BitacoraProvider;
import com.example.medicinapp.utils.ViewedMessageHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;

import de.hdodenhof.circleimageview.CircleImageView;

public class BitacoraDetailActivity extends AppCompatActivity {

    TextView mTextViewEmotion;
    TextView mTextViewActivity;
    TextView mTextViewNotes;
    TextView mTextViewEmotionDate;
    ImageView mImageViewEmotion;
    ImageView mImageViewDeleteEmotion;
    CircleImageView mCircleImageViewBack;

    BitacoraProvider mBitacoraProvider;
    AuthProvider mAuthProvider;

    String mExtraBitacoraId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitacora_detail);

        mTextViewEmotion = findViewById(R.id.textViewEmotion);
        mTextViewEmotionDate = findViewById(R.id.textViewEmotionDate);
        mTextViewActivity = findViewById(R.id.textViewActivity);
        mTextViewNotes = findViewById(R.id.textViewNotes);
        mImageViewEmotion = findViewById(R.id.imageViewEmotion);
        mImageViewDeleteEmotion = findViewById(R.id.imageViewDeleteEmotion);
        mCircleImageViewBack = findViewById(R.id.circleImageBack);

        mBitacoraProvider = new BitacoraProvider();
        mAuthProvider = new AuthProvider();

        mExtraBitacoraId = getIntent().getStringExtra("id");

        mCircleImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mImageViewDeleteEmotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmDelete(mExtraBitacoraId);
            }
        });

        getBitacora();

    }

    @Override
    protected void onStart() {
        super.onStart();
        ViewedMessageHelper.updateOnline(true, BitacoraDetailActivity.this);
    }



    @Override
    protected void onPause() {
        super.onPause();
        ViewedMessageHelper.updateOnline(false, BitacoraDetailActivity.this);
    }

    private void showConfirmDelete(final String mExtraBitacoraId) {
        new AlertDialog.Builder(BitacoraDetailActivity.this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Eliminar página de bitácora")
                .setMessage("¿Estas seguro de borrar esta página?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteBitacora(mExtraBitacoraId);
                    }
                })
                .setNegativeButton("NO", null)
                .show();
    }

    private void deleteBitacora(String mExtraBitacoraId) {
        mBitacoraProvider.delete(mExtraBitacoraId).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(BitacoraDetailActivity.this, "La hisoria de bitácora se elimino correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BitacoraDetailActivity.this, BinnacleFragment.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(BitacoraDetailActivity.this, "No se pudo eliminar la historia de bitácora", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getBitacora() {
        mBitacoraProvider.getBitacoraById(mExtraBitacoraId).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    if (documentSnapshot.contains("activity")) {
                        String activity = documentSnapshot.getString("activity");
                        mTextViewActivity.setText(activity);
                    }
                    if (documentSnapshot.contains("emotion")) {
                        String emotion = documentSnapshot.getString("emotion");
                        mTextViewEmotion.setText(emotion);
                        getEmotion(emotion);
                    }
                    if (documentSnapshot.contains("note")) {
                        String note = documentSnapshot.getString("note");
                        mTextViewNotes.setText(note);
                    }
                    if (documentSnapshot.contains("timestamp")) {
                        Long date = documentSnapshot.getLong("timestamp");
                        getDate(date);
                    }
                }

            }
        });
    }

    private void getEmotion(String emotion) {
        if(emotion.equals("Feliz")){
          mImageViewEmotion.setImageResource(R.drawable.ic_confident);
        } else if (emotion.equals("Aburrido")) {
            mImageViewEmotion.setImageResource(R.drawable.ic_bored);
        } else if (emotion.equals("Triste")) {
            mImageViewEmotion.setImageResource(R.drawable.ic_sad);
        } else if (emotion.equals("Enfadado")) {
            mImageViewEmotion.setImageResource(R.drawable.ic_angry);
        } else if (emotion.equals("Asustado")) {
            mImageViewEmotion.setImageResource(R.drawable.ic_scared);
        }
    }

    private void getDate(long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String Time = sdf.format(date);
        mTextViewEmotionDate.setText(Time);
    }

}