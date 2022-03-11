package com.example.coinedone;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://api.mocklets.com/mock68182/";
    @GET("screentime")
    Call<List<ScreenTimePojo>> gettime_data();
}
