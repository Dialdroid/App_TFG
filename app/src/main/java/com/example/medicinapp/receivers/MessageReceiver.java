package com.example.medicinapp.receivers;

import android.app.NotificationManager;
import android.app.RemoteInput;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.medicinapp.models.Message;
import com.example.medicinapp.providers.MessagesProvider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.Date;

import static com.example.medicinapp.service.MyFirebaseMessagingClient.NOTIFICATION_REPLY;

public class MessageReceiver extends BroadcastReceiver {

    String mExtraIdSender;
    String mExtraIdReceiver;
    String mExtraIdChat;
    int mExtraIdNotification;

    @Override
    public void onReceive(Context context, Intent intent) {
        mExtraIdSender = intent.getExtras().getString("idSender");
        mExtraIdReceiver = intent.getExtras().getString("idReceiver");
        mExtraIdChat = intent.getExtras().getString("idChat");
        mExtraIdNotification = intent.getExtras().getInt("idNotification");

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(mExtraIdNotification);

        String message = getMessageText(intent).toString();

        sendMessage(message);
    }

    private void sendMessage(String messageText) {
        final Message message = new Message();
        message.setIdChat(mExtraIdChat);
        message.setIdSender(mExtraIdReceiver);
        message.setIdReceiver(mExtraIdSender);
        message.setTimestamp(new Date().getTime());
        message.setViewed(false);
        message.setIdChat(mExtraIdChat);
        message.setMessage(messageText);

        MessagesProvider messagesProvider = new MessagesProvider();
        messagesProvider.create(message).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    //getToken(message);
                }
            }
        });
    }

    private CharSequence getMessageText(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getCharSequence(NOTIFICATION_REPLY);
        }
        return null;
    }
}
