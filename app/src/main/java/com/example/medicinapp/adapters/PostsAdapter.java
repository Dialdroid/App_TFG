package com.example.medicinapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicinapp.activities.PostDetailActivity;
import com.example.medicinapp.models.Like;
import com.example.medicinapp.models.Post;
import com.example.medicinapp.R;
import com.example.medicinapp.providers.LikesProvider;
import com.example.medicinapp.providers.PostProvider;
import com.example.medicinapp.providers.UserProvider;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;

import java.util.Date;


public class PostsAdapter extends FirestoreRecyclerAdapter<Post, PostsAdapter.ViewHolder> {

    Context context;
    UserProvider mUserProvider;
    LikesProvider mLikesProvider;

    public PostsAdapter(FirestoreRecyclerOptions<Post> options, Context context) {
        super(options);
        this.context = context;
        mUserProvider = new UserProvider();
        mLikesProvider = new LikesProvider();
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull final Post post) {

        DocumentSnapshot document = getSnapshots().getSnapshot(position);
        final String postId = document.getId();
     
        holder.textViewTitle.setText(post.getTitle().toUpperCase());
        holder.textViewDescription.setText(post.getDescription());
        if (post.getImage1() != null) {
            if (!post.getImage1().isEmpty()) {
                Picasso.with(context).load(post.getImage1()).into(holder.imageViewPost);
            }
        }
        holder.viewHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PostDetailActivity.class);
                intent.putExtra("id", postId);
                context.startActivity(intent);
            }
        });

        holder.imageViewLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Like like = new Like();
                like.setIdUser(post.getIdUser());
                like.setIdPost(postId);
                like.setTimestamp(new Date().getTime());
                like(like);
            }
        });

        getUserInfo(post.getIdUser(), holder);
    }

    private void like(Like like) {

        mLikesProvider.create(like);

    }

    private void getUserInfo(String idUser, final ViewHolder holder) {
        mUserProvider.getUser(idUser).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    if (documentSnapshot.contains("username")){
                        String username = documentSnapshot.getString("username");
                        holder.textViewUsername.setText("By: " + username.toUpperCase());
                    }
                }
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_post, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewUsername;
        TextView textViewLike;
        ImageView imageViewPost;
        ImageView imageViewLike;
        View viewHolder;

        public ViewHolder(View view) {
            super(view);
            textViewTitle = view.findViewById(R.id.textViewTitlePostcard);
            textViewDescription = view.findViewById(R.id.textViewDescriptionPostcard);
            textViewUsername = view.findViewById(R.id.textViewUsernamePostCard);
            textViewLike = view.findViewById(R.id.textViewLike);
            imageViewPost = view.findViewById(R.id.imageViewPostCard);
            imageViewLike = view.findViewById(R.id.imageViewLike);
            viewHolder = view;
        }
    }

}
