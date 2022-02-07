package com.example.medicinapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.medicinapp.R;
import com.example.medicinapp.fragments.ChatsFragment;
import com.example.medicinapp.fragments.BinnacleFragment;
import com.example.medicinapp.fragments.HomeFragment;
import com.example.medicinapp.fragments.ProfileFragment;
import com.example.medicinapp.providers.AuthProvider;
import com.example.medicinapp.providers.TokenProvider;
import com.example.medicinapp.providers.UserProvider;
import com.example.medicinapp.utils.ViewedMessageHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    TokenProvider mTokenProvider;
    AuthProvider mAuthProvider;
    UserProvider mUsersProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        mTokenProvider = new TokenProvider();
        mAuthProvider = new AuthProvider();
        mUsersProvider = new UserProvider();
        openFragment(new HomeFragment());
        createToken();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ViewedMessageHelper.updateOnline(true, HomeActivity.this);
    }



    @Override
    protected void onPause() {
        super.onPause();
        ViewedMessageHelper.updateOnline(false, HomeActivity.this);
    }


    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                   if(item.getItemId() == R.id.item_home){
                       openFragment(new HomeFragment());
                   } else if(item.getItemId() == R.id.item_chat) {
                       openFragment(new ChatsFragment());
                   } else if(item.getItemId() == R.id.item_profile) {
                       openFragment(new ProfileFragment());
                   } else if(item.getItemId() == R.id.item_book) {
                       openFragment(new BinnacleFragment());
                   }
                   return true;
                }
            };

    private void createToken() {
        mTokenProvider.create(mAuthProvider.getUID());
    }
}