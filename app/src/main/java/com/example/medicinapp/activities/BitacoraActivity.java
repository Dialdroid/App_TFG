package com.example.medicinapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medicinapp.R;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;


public class BitacoraActivity extends AppCompatActivity implements View.OnClickListener {

    Button mBtnHappy;
    Button mBtnBored;
    Button mBtnSad;
    Button mBtnAngry;
    Button mBtnScared;
    CircleImageView mCircleImageViewBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitacora);
        mBtnHappy = findViewById(R.id.btnHappy);
        mBtnBored = findViewById(R.id.btnBored);
        mBtnSad = findViewById(R.id.btnSad);
        mBtnAngry = findViewById(R.id.btnAngry);
        mBtnScared = findViewById(R.id.btnScared);
        mCircleImageViewBack = findViewById(R.id.circleImageBack);


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

                break;
        }
    }
}