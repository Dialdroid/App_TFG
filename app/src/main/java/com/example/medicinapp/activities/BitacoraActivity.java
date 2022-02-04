package com.example.medicinapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;

import com.example.medicinapp.R;
import com.example.medicinapp.models.Bitacora;
import com.example.medicinapp.providers.BitacoraProvider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;


public class BitacoraActivity extends AppCompatActivity implements View.OnClickListener {

    Button mBtnHappy;
    Button mBtnBored;
    Button mBtnSad;
    Button mBtnAngry;
    Button mBtnScared;
    Button mBtnGuardar;
    TextInputEditText mTextInputActivity;
    TextInputEditText mTextInputNote;
    CircleImageView mCircleImageViewBack;
    BitacoraProvider mProvider;
    String mActivity = "";
    String mNote = "";
    String mEmotion = "";
    AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitacora);

        mProvider = new BitacoraProvider();

        mBtnHappy = findViewById(R.id.btnHappy);
        mBtnBored = findViewById(R.id.btnBored);
        mBtnSad = findViewById(R.id.btnSad);
        mBtnAngry = findViewById(R.id.btnAngry);
        mBtnScared = findViewById(R.id.btnScared);
        mBtnGuardar = findViewById(R.id.btnGuardar);
        mCircleImageViewBack = findViewById(R.id.circleImageBack);

        mTextInputActivity = findViewById(R.id.textInputActivity);
        mTextInputNote = findViewById(R.id.textInpuNote);

        mDialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Espere un momento")
                .setCancelable(false)
                .build();


        mBtnHappy.setOnClickListener(this);
        mBtnBored.setOnClickListener(this);
        mBtnSad.setOnClickListener(this);
        mBtnAngry.setOnClickListener(this);
        mBtnScared.setOnClickListener(this);

        mCircleImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBtnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEmotion();
            }
        });

    }

    private void saveEmotion() {
        mActivity = mTextInputActivity.getText().toString();
        mNote = mTextInputNote.getText().toString();
        if (!mActivity.isEmpty() && !mNote.isEmpty() && !mEmotion.isEmpty()){
                createBitacora(mActivity, mNote, mEmotion);
        } else {
            Toast.makeText(this, "Completa todos campos", Toast.LENGTH_SHORT).show();
        }

    }

    private void createBitacora(String mActivity, String mNote, String mEmotion) {
        mDialog.show();
        Bitacora bitacora = new Bitacora();
        bitacora.setActivity(mActivity);
        bitacora.setNote(mNote);
        bitacora.setEmotion(mEmotion);
        bitacora.setTimestamp(new Date().getTime());
        mProvider.save(bitacora).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                mDialog.dismiss();
                if (task.isSuccessful()){
                    clearBitacora();
                    Toast.makeText(BitacoraActivity.this, "La informacion se almaceno correctamente", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(BitacoraActivity.this, "No se pudo almacenar la informacion", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void clearBitacora() {

        mTextInputActivity.setText("");
        mTextInputNote.setText("");

        mActivity = "";
        mNote = "";
        mEmotion = "";

        mBtnHappy.setBackgroundTintList(getResources().getColorStateList(R.color.white));
        mBtnHappy.setBackgroundResource(R.drawable.ic_confident_white);

        mBtnBored.setBackgroundTintList(getResources().getColorStateList(R.color.white));
        mBtnBored.setBackgroundResource(R.drawable.ic_bored_white);

        mBtnSad.setBackgroundTintList(getResources().getColorStateList(R.color.white));
        mBtnSad.setBackgroundResource(R.drawable.ic_sad_white);

        mBtnAngry.setBackgroundTintList(getResources().getColorStateList(R.color.white));
        mBtnAngry.setBackgroundResource(R.drawable.ic_angry_white);

        mBtnScared.setBackgroundTintList(getResources().getColorStateList(R.color.white));
        mBtnScared.setBackgroundResource(R.drawable.ic_scared_white);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHappy:
                Toast.makeText(this, "Estás feliz", Toast.LENGTH_SHORT).show();
                mBtnHappy.setBackgroundTintList(null);
                mBtnHappy.setBackgroundResource(R.drawable.ic_confident);

                mBtnBored.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                mBtnBored.setBackgroundResource(R.drawable.ic_bored_white);

                mBtnSad.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                mBtnSad.setBackgroundResource(R.drawable.ic_sad_white);

                mBtnAngry.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                mBtnAngry.setBackgroundResource(R.drawable.ic_angry_white);

                mBtnScared.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                mBtnScared.setBackgroundResource(R.drawable.ic_scared_white);

                mEmotion = "Feliz";

                break;

            case R.id.btnBored:
                Toast.makeText(this, "Estás aburrido", Toast.LENGTH_SHORT).show();

                mBtnHappy.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                mBtnHappy.setBackgroundResource(R.drawable.ic_confident_white);

                mBtnBored.setBackgroundTintList(null);
                mBtnBored.setBackgroundResource(R.drawable.ic_bored);

                mBtnSad.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                mBtnSad.setBackgroundResource(R.drawable.ic_sad_white);

                mBtnAngry.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                mBtnAngry.setBackgroundResource(R.drawable.ic_angry_white);

                mBtnScared.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                mBtnScared.setBackgroundResource(R.drawable.ic_scared_white);

                mEmotion = "Aburrido";

                break;

            case R.id.btnSad:
                Toast.makeText(this, "Estás triste", Toast.LENGTH_SHORT).show();

                mBtnHappy.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                mBtnHappy.setBackgroundResource(R.drawable.ic_confident_white);

                mBtnBored.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                mBtnBored.setBackgroundResource(R.drawable.ic_bored_white);

                mBtnSad.setBackgroundTintList(null);
                mBtnSad.setBackgroundResource(R.drawable.ic_sad);

                mBtnAngry.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                mBtnAngry.setBackgroundResource(R.drawable.ic_angry_white);

                mBtnScared.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                mBtnScared.setBackgroundResource(R.drawable.ic_scared_white);

                mEmotion = "Triste";

                break;

            case R.id.btnAngry:
                Toast.makeText(this, "Estás enojado", Toast.LENGTH_SHORT).show();

                mBtnHappy.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                mBtnHappy.setBackgroundResource(R.drawable.ic_confident_white);

                mBtnBored.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                mBtnBored.setBackgroundResource(R.drawable.ic_bored_white);

                mBtnSad.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                mBtnSad.setBackgroundResource(R.drawable.ic_sad_white);

                mBtnAngry.setBackgroundTintList(null);
                mBtnAngry.setBackgroundResource(R.drawable.ic_angry);

                mBtnScared.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                mBtnScared.setBackgroundResource(R.drawable.ic_scared_white);

                mEmotion = "Enfadado";

                break;

            case R.id.btnScared:
                Toast.makeText(this, "Estás asustado", Toast.LENGTH_SHORT).show();

                mBtnHappy.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                mBtnHappy.setBackgroundResource(R.drawable.ic_confident_white);

                mBtnBored.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                mBtnBored.setBackgroundResource(R.drawable.ic_bored_white);

                mBtnSad.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                mBtnSad.setBackgroundResource(R.drawable.ic_sad_white);

                mBtnAngry.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                mBtnAngry.setBackgroundResource(R.drawable.ic_angry_white);

                mBtnScared.setBackgroundTintList(null);
                mBtnScared.setBackgroundResource(R.drawable.ic_scared);

                mEmotion = "Asustado";

                break;
        }
    }
}