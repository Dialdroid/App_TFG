package com.example.medicinapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.medicinapp.R;
import com.example.medicinapp.models.User;
import com.example.medicinapp.providers.AuthProvider;
import com.example.medicinapp.providers.UserProvider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CompleteProfileActivity extends AppCompatActivity {

    TextInputEditText mTextInputUserName;
    Button mButtonConfirm;
    AuthProvider mAuthProvider;
    UserProvider mUserProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);

        mTextInputUserName = findViewById(R.id.textInputUsername);
        mButtonConfirm = findViewById(R.id.btnConfirm);

        mAuthProvider = new AuthProvider();
        mUserProvider = new UserProvider();

        mButtonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register(){
        String username = mTextInputUserName.getText().toString();
        if (!username.isEmpty() ) {
            updateUser(username);
        } else {
            Toast.makeText(this, "Para continuar inserta todos los campos", Toast.LENGTH_LONG).show();
        }

    }

    private void updateUser(final String userName) {
        String id = mAuthProvider.getUID();
        User user = new User();
        user.setId(id);
        user.setUsername(userName);
        mUserProvider.updateUser(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(CompleteProfileActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(CompleteProfileActivity.this, "El usuario no se almaceno en la base de datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}