package com.avd.covidtracker.Pojo;

import com.google.gson.annotations.SerializedName;

public class AllCovids {

    @SerializedName("active")
    private long activeCases;

    @SerializedName("confirmed")
    private long confirmedCases;

    @SerializedName("recovered")
    private long recoveredCases;

    @SerializedName("deaths")
    private long deathCases;

//    public AllCovids(long activeCases, long confirmedCases, long recoveredCases, long deathCases) {
//        this.activeCases = activeCases;
//        this.confirmedCases = confirmedCases;
//        this.recoveredCases = recoveredCases;
//        this.deathCases = deathCases;
//    }

    public long getActiveCases() {
        return activeCases;
    }

    public void setActiveCases(long activeCases) {
        this.activeCases = activeCases;
    }

    public long getConfirmedCases() {
        return confirmedCases;
    }

    public void setConfirmedCases(long confirmedCases) {
        this.confirmedCases = confirmedCases;
    }

    public long getRecoveredCases() {
        return recoveredCases;
    }

    public void setRecoveredCases(long recoveredCases) {
        this.recoveredCases = recoveredCases;
    }

    public long getDeathCases() {
        return deathCases;
    }

    public void setDeathCases(long deathCases) {
        this.deathCases = deathCases;
    }
}
