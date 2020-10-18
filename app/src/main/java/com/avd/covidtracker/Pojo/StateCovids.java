package com.avd.covidtracker.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StateCovids {

    @SerializedName("id")
    private String id;

    @SerializedName("state")
    private String state;

    @SerializedName("active")
    private long active;

    @SerializedName("confirmed")
    private long confirmed;

    @SerializedName("recovered")
    private long recovered;

    @SerializedName("deaths")
    private long deaths;

    @SerializedName("districtData")
    private List<DistCovids> districts;

    public StateCovids() { }

    public StateCovids(String id, String state) {
        this.id = id;
        this.state = state;
    }

    public StateCovids(String id, String state, long active, long confirmed, long recovered, long deaths, List<DistCovids> districts) {
        this.id = id;
        this.state = state;
        this.active = active;
        this.confirmed = confirmed;
        this.recovered = recovered;
        this.deaths = deaths;
        this.districts = districts;
    }

    public List<DistCovids> getDistricts() {
        return districts;
    }

    public String getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public long getActive() {
        return active;
    }

    public long getConfirmed() {
        return confirmed;
    }

    public long getRecovered() {
        return recovered;
    }

    public long getDeaths() {
        return deaths;
    }

    @Override
    public String toString() {
        return getState();
    }
}
