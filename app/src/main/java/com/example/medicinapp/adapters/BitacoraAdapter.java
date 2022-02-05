package com.example.medicinapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicinapp.R;
import com.example.medicinapp.activities.BitacoraDetailActivity;
import com.example.medicinapp.activities.PostDetailActivity;
import com.example.medicinapp.models.Bitacora;
import com.example.medicinapp.providers.BitacoraProvider;
import com.example.medicinapp.utils.RelativeTime;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.protobuf.StringValue;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BitacoraAdapter extends FirestoreRecyclerAdapter<Bitacora, BitacoraAdapter.ViewHolder>{

    Context context;
    BitacoraProvider mBitacoraProvider;

    public BitacoraAdapter(FirestoreRecyclerOptions<Bitacora> options, Context context){
        super(options);
        this.context = context;
        mBitacoraProvider = new BitacoraProvider();
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull final ViewHolder holder, int position, @NonNull @NotNull Bitacora bitacora) {

        DocumentSnapshot document = getSnapshots().getSnapshot(position);
        final String bitacoraId = document.getId();

        holder.textViewEmotion.setText(bitacora.getEmotion());
        holder.textViewDescription.setText(bitacora.getActivity());
        getEmote(bitacora.getEmotion(),holder);
        getDate(bitacora.getTimestamp(), holder, bitacora);
        getTime(bitacora.getTimestamp(), holder, bitacora);

        holder.viewHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BitacoraDetailActivity.class);
                context.startActivity(intent);
            }
        });

    }

    private void getTime(long timestamp, ViewHolder holder, Bitacora bitacora) {
        String relativeTime = RelativeTime.getTimeAgo(timestamp, context);
        holder.textViewTime.setText(String.valueOf(relativeTime));
    }

    private void getDate(Long date, ViewHolder holder, Bitacora bitacora) {
       SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
       String Time = sdf.format(date);
       holder.textViewDate.setText(Time);
    }

    private void getEmote(String emotion,ViewHolder holder) {
        if(emotion.equals("Feliz")){
            holder.imageViewEmotion.setImageResource(R.drawable.ic_confident);
        } else if (emotion.equals("Aburrido")) {
            holder.imageViewEmotion.setImageResource(R.drawable.ic_bored);
        } else if (emotion.equals("Triste")) {
            holder.imageViewEmotion.setImageResource(R.drawable.ic_sad);
        } else if (emotion.equals("Enfadado")) {
            holder.imageViewEmotion.setImageResource(R.drawable.ic_angry);
        } else if (emotion.equals("Asustado")) {
            holder.imageViewEmotion.setImageResource(R.drawable.ic_scared);
        }

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
        View viewHolder;

        public ViewHolder(View view){
            super(view);
            textViewDate = view.findViewById(R.id.textViewDateBitacora);
            textViewEmotion = view.findViewById(R.id.textViewTitleEmotioncard);
            textViewDescription = view.findViewById(R.id.textViewDescriptionEmotioncard);
            textViewTime = view.findViewById(R.id.textViewRelativeTimeBitacora);
            imageViewEmotion = view.findViewById(R.id.imageViewEmotion);
            viewHolder = view;
        }

    }

}
