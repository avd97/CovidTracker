package com.avd.covidtracker.Connection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://api.covidindiatracker.com/";
//                                                                      https://api.covidindiatracker.com/state_data.json
    public static Retrofit retrofit = null;

    public static Retrofit getApiClient() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
