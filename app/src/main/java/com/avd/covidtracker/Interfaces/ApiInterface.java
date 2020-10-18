package com.avd.covidtracker.Interfaces;

import com.avd.covidtracker.Pojo.AllCovids;
import com.avd.covidtracker.Pojo.StateCovids;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("total.json")
    Call<AllCovids> getTotalCases();

    @GET("state_data.json")
    Call<List<StateCovids>> getStateCases();

}
