package com.example.medicinapp.service;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;

import com.example.medicinapp.R;
import com.example.medicinapp.channel.NotificationHelper;
import com.example.medicinapp.models.Message;
import com.example.medicinapp.receivers.MessageReceiver;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Random;

public class MyFirebaseMessagingClient extends FirebaseMessagingService {

    public static final String NOTIFICATION_REPLY = "NotificationReply";

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Map<String, String> data = remoteMessage.getData();
        String title = data.get("title");
        String body = data.get("body");
        if (title != null) {
            if (title.equals("NUEVO MENSAJE")) {
                showNotificationMessage(data);
            }
            else {
                showNotification(title, body);
            }
        }
    }

    private void showNotification(String title, String body) {
        NotificationHelper notificationHelper = new NotificationHelper(getBaseContext());
        NotificationCompat.Builder builder = notificationHelper.getNotification(title, body);
        Random random = new Random();
        int n = random.nextInt(10000);
        notificationHelper.getManager().notify(n, builder.build());
    }

    private void showNotificationMessage(Map<String, String> data) {
        String title = data.get("title");
        String body = data.get("body");
        final String usernameSender = data.get("usernameSender");
        final String usernameReceiver = data.get("usernameReceiver");
        final String lastMessage = data.get("lastMessage");
        String messagesJSON = data.get("messages");
        final String imageSender = data.get("imageSender");
        final String imageReceiver = data.get("imageReceiver");
        final String idSender = data.get("idSender");
        final String idReceiver = data.get("idReceiver");
        final String idChat = data.get("idChat");
        final int idNotification = Integer.parseInt(data.get("idNotification"));

        Intent intent = new Intent(this, MessageReceiver.class);
        intent.putExtra("idSender", idSender);
        intent.putExtra("idReceiver", idReceiver);
        intent.putExtra("idChat", idChat);
        intent.putExtra("idNotification", idNotification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteInput remoteInput = new RemoteInput.Builder(NOTIFICATION_REPLY).setLabel("Tu mensaje...").build();

        final NotificationCompat.Action action = new NotificationCompat.Action.Builder(
                R.mipmap.ic_launcher,
                "Responder",
                pendingIntent)
                .addRemoteInput(remoteInput)
                .build();

        Gson gson = new Gson();
        final Message[] messages = gson.fromJson(messagesJSON, Message[].class);

        new Handler(Looper.getMainLooper())
                .post(new Runnable() {
                    @Override
                    public void run() {
                        Picasso.with(getApplicationContext())
                                .load(imageSender)
                                .into(new Target() {
                                    @Override
                                    public void onBitmapLoaded(final Bitmap bitmapSender, Picasso.LoadedFrom from) {

                                        Picasso.with(getApplicationContext())
                                                .load(imageReceiver)
                                                .into(new Target() {
                                                    @Override
                                                    public void onBitmapLoaded(Bitmap bitmapReceiver, Picasso.LoadedFrom from) {
                                                        NotificationHelper notificationHelper = new NotificationHelper(getBaseContext());
                                                        NotificationCompat.Builder builder =
                                                                notificationHelper.getNotificationMessage(
                                                                        messages,
                                                                        usernameSender,
                                                                        usernameReceiver,
                                                                        lastMessage,
                                                                        bitmapSender,
                                                                        bitmapReceiver,
                                                                        action
                                                                );
                                                        notificationHelper.getManager().notify(idNotification, builder.build());
                                                    }

                                                    @Override
                                                    public void onBitmapFailed(Drawable errorDrawable) {

                                                    }

                                                    @Override
                                                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                                                    }
                                                });

                                    }

                                    @Override
                                    public void onBitmapFailed(Drawable errorDrawable) {

                                    }

                                    @Override
                                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                                    }
                                });
                    }
                });


    }

}
