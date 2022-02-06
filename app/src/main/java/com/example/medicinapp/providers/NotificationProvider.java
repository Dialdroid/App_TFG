package com.example.medicinapp.providers;

import com.example.medicinapp.models.FCMBody;
import com.example.medicinapp.models.FCMResponse;
import com.example.medicinapp.retrofit.IFCMApi;
import com.example.medicinapp.retrofit.RetrofitClient;

import retrofit2.Call;

public class NotificationProvider {

    private String url = "https://fcm.googleapis.com";

    public NotificationProvider() {

    }

    public Call<FCMResponse> sendNotification(FCMBody body) {
        return RetrofitClient.getClient(url).create(IFCMApi.class).send(body);
    }

}
