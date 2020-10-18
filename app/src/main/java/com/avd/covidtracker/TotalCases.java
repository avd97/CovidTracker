package com.avd.covidtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.avd.covidtracker.Connection.ApiClient;
import com.avd.covidtracker.Interfaces.ApiInterface;
import com.avd.covidtracker.Pojo.AllCovids;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TotalCases extends AppCompatActivity {

    TextView tv_active, tv_confirm, tv_death, tv_recover;
    ApiInterface apiInterface;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_cases);

        tv_active = findViewById(R.id.tv_active);
        tv_confirm = findViewById(R.id.tv_confirm);
        tv_death = findViewById(R.id.tv_death);
        tv_recover = findViewById(R.id.tv_recover);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);

        getData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getData() {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<AllCovids> call = apiInterface.getTotalCases();

        call.enqueue(new Callback<AllCovids>() {
            @Override
            public void onResponse(Call<AllCovids> call, Response<AllCovids> response) {

                long active = response.body().getActiveCases();
                long confirm = response.body().getConfirmedCases();
                long death = response.body().getDeathCases();
                long recovered = response.body().getRecoveredCases();

                Log.d("ACTIVE", String.valueOf(active));
                Log.d("CONFIRM", String.valueOf(confirm));
                Log.d("RECOVERED", String.valueOf(recovered));
                Log.d("DEATH", String.valueOf(death));

                tv_active.setText(""+active);
                tv_confirm.setText(""+confirm);
                tv_recover.setText(""+recovered);
                tv_death.setText(""+death);
            }

            @Override
            public void onFailure(Call<AllCovids> call, Throwable t) {
                Toast.makeText(TotalCases.this, "Unable to fetch", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        startActivity(new Intent(this,StateCases.class));
//    }
}