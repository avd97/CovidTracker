package com.avd.covidtracker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.avd.covidtracker.Connection.ApiClient;
import com.avd.covidtracker.Interfaces.ApiInterface;
import com.avd.covidtracker.Pojo.DistCovids;
import com.avd.covidtracker.Pojo.StateCovids;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StateCases extends AppCompatActivity {

    boolean isActive = true;
    SwipeRefreshLayout swipeRefreshLayout;
    ApiInterface apiInterface;

    Spinner spinState;
    Spinner spinDist;

    List<StateCovids> stateCovidList;
    List<DistCovids> distCovidList;
    ArrayAdapter<StateCovids> adapter;
    ArrayAdapter<DistCovids> distAdapter;

    StateCovids stateCovids;

    TextView tvStActive,tvStConfirm, tvStRecover, tvStDeath, tvDistConfirm;

    String a,c,r,d;

    List<StateCovids> stateCovidList2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_cases);

        swipeRefreshLayout = findViewById(R.id.swipeStateRefresh);
        spinState = findViewById(R.id.spinState);
        spinDist = findViewById(R.id.spinDist);

        stateCovids = new StateCovids();
        tvStActive = findViewById(R.id.tv_stActive);
        tvStConfirm = findViewById(R.id.tv_stConfirm);
        tvStRecover = findViewById(R.id.tv_stRecover);
        tvStDeath = findViewById(R.id.tv_stDeath);
        tvDistConfirm = findViewById(R.id.tv_distConfirm);

        stateCovidList = new ArrayList<>();
        stateCovidList.clear();

        stateCovidList2 = new ArrayList<>();
        stateCovidList2.clear();

        adapter = new ArrayAdapter<StateCovids>(this, android.R.layout.simple_spinner_item,stateCovidList);

        distCovidList = new ArrayList<>();
        distCovidList.clear();
        distAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,distCovidList);
        getData();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        // for state spinner
        spinState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            //  System.out.println("LIST: "+stateCovidList);
                tvStActive.setText(String.valueOf(stateCovidList.get(i).getActive()));
                tvStConfirm.setText(String.valueOf(stateCovidList.get(i).getConfirmed()));
                tvStRecover.setText(String.valueOf(stateCovidList.get(i).getRecovered()));
                tvStDeath.setText(String.valueOf(stateCovidList.get(i).getDeaths()));

                distCovidList.clear();
                for(DistCovids district:stateCovidList.get(i).getDistricts()) {
                    distCovidList.add(district);
                }
//                Collections.sort(new DistCovids().getName());
                distAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinDist.setAdapter(distAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(StateCases.this, "Select an item from dropdown.", Toast.LENGTH_SHORT).show();
            }
        });


        // for district spinner
        spinDist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tvDistConfirm.setText(String.valueOf(distCovidList.get(i).getConfirmed()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        if(isActive)
//            refresh(30000);
    }

    private void getData() {
        try {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<StateCovids>> call = apiInterface.getStateCases();

        call.enqueue(new Callback<List<StateCovids>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<StateCovids>> call, Response<List<StateCovids>> response) {
                if (response.isSuccessful()) {
                    for (StateCovids row : response.body()) {
                        String id = "" + row.getId();
                        String name = "" + row.getState();
                        long ac = row.getActive();
                        long con = row.getConfirmed();
                        long rec = row.getRecovered();
                        long dt = row.getDeaths();


                        StateCovids stateCovids = new StateCovids(id, name, ac, con, rec, dt, row.getDistricts());
//                        StateCovids stateCovids = (StateCovids) Collections.singletonList(new StateCovids(id, name, ac, con, rec, dt, row.getDistricts()));

                        stateCovidList.add(stateCovids);

//                        System.out.println("state: "+stateCovidList);

//                        Collections.sort(stateCovids.getState());

//                        stateCovidList =  stateCovidList.stream().sorted(Comparator.comparing(StateCovids::getState)).collect(Collectors.toList());

//                        Comparator c = Collections.reverseOrder();
//                        Collections.sort(stateCovidList,c);
                    }
//                        StateCovids[] item = new StateCovids[stateCovidList.toArray().length];
//                        // item = (String[]) stateCovidList.toArray();
//                        int i = 0;
//                        for (Object s : stateCovidList.toArray()) {
//                            StateCovids cov = (StateCovids) s;
//                            item[i] = cov;
//                            i++;
//                        }
//                        System.out.println("----------------------------------------------------------------");
//                        System.out.println(item);
//                        int listSize = item.length;
//                        for (int a = 0; a < listSize - 1; a++) {
//                            for (int b = a + 1; b < item.length; b++) {
//                                if (String.valueOf(item[a].getState()).compareTo(String.valueOf(item[b].getState())) > 0) {
//                                    StateCovids temp = item[a];
//                                    item[a] = item[b];
//                                    item[b] = temp;
//                                }
//                            }
//                        }   // end for loop

                   // stateCovidList.clear();
//                        stateCovidList = Arrays.asList(item);
//                        System.out.println(stateCovidList);
                        //System.out.println(stateCovidList2);
//                        List<StateCovids> list1 = new ArrayList<>();
//                        Collections.addAll(list1, item);
//                        System.out.println(list1);

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinState.setAdapter(adapter);

                    }


            }

            @Override
            public void onFailure(Call<List<StateCovids>> call, Throwable t) {
                Toast.makeText(StateCases.this, "UNABLE TO FETCH, Check your internet connection.", Toast.LENGTH_LONG).show();
            }
        });
    } catch (Exception e){
            Toast.makeText(this, ""+e, Toast.LENGTH_LONG).show();
        }
    }

    private void refresh(long miliseconds) {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                getData();
            }
        };
        handler.postDelayed(runnable,miliseconds);
//        Toast.makeText(this, "Refreshed.", Toast.LENGTH_SHORT).show();
    }

    public void totalCases(View view) {
        startActivity(new Intent(this,TotalCases.class));
    }
}