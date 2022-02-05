package com.example.medicinapp.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicinapp.R;
import com.example.medicinapp.models.Bitacora;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import org.jetbrains.annotations.NotNull;

public class BitacoraAdapter extends FirestoreRecyclerAdapter<Bitacora, BitacoraAdapter.ViewHolder>{

    Context context;

    public BitacoraAdapter(FirestoreRecyclerOptions<Bitacora> options, Context context){
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position, @NonNull @NotNull Bitacora bitacora) {
        holder.textViewEmotion.setText(bitacora.getEmotion());
        holder.textViewDescription.setText(bitacora.getActivity());

    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_binnacle, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDate;
        TextView textViewEmotion;
        TextView textViewDescription;
        TextView textViewTime;
        ImageView imageViewEmotion;

        public ViewHolder(View view){
            super(view);
            textViewDate = view.findViewById(R.id.textViewDateBitacora);
            textViewEmotion = view.findViewById(R.id.textViewTitleEmotioncard);
            textViewDescription = view.findViewById(R.id.textViewDescriptionEmotioncard);
            textViewTime = view.findViewById(R.id.textViewRelativeTimeBitacora);
            imageViewEmotion = view.findViewById(R.id.imageViewEmotion);
        }

    }

}
