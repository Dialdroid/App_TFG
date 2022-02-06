package com.example.medicinapp.retrofit;

import com.example.medicinapp.models.FCMBody;
import com.example.medicinapp.models.FCMResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IFCMApi {

    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAAC82pPQQ:APA91bFjjt-8GpweM3x6u87SqKNWKUu7aAHRcSRnb7Eg0Zgb-RMSN4xwNqNdh4UyLH-w2gMfJjFyxpfkC_dAw9GKlwE5L3xF-ygFp9jvgvxQSmWuI8jxrg4gBmgoCMzF5bgwDfJEbvQK"
    })
    @POST("fcm/send")
    Call<FCMResponse> send(@Body FCMBody body);

}
