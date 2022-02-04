package com.example.medicinapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;

import com.example.medicinapp.R;
import com.example.medicinapp.activities.BitacoraActivity;
import com.example.medicinapp.providers.BitacoraProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class BinnacleFragment extends Fragment {

        View mView;
        FloatingActionButton mFab;
        Toolbar mToolbar;
        RecyclerView mRecyclerView;
        BitacoraProvider mProvider;

        public BinnacleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_binnacle, container, false);
        mFab = mView.findViewById(R.id.fabBitacora);
        mToolbar = mView.findViewById(R.id.toolbar);
        mRecyclerView = mView.findViewById(R.id.recyclerViewBitacora);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Bitacora");

        mProvider = new BitacoraProvider();

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBitacora();
            }
        });
        return mView;
    }

    private void goToBitacora() {
        Intent intent = new Intent(getContext(), BitacoraActivity.class);
        startActivity(intent);
    }
}