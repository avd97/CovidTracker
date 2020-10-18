package com.avd.covidtracker.Pojo;

import com.google.gson.annotations.SerializedName;

public class DistCovids implements Comparable<DistCovids> {

    //  "id": "Pune",
    //  "state": null,
    //  "name": "Pune",
    //  "confirmed": 298227,
    //  "recovered": null,
    //  "deaths": null,
    //  "oldConfirmed": 0,
    //  "oldRecovered": null,
    //  "oldDeaths": null,
    //  "zone": "NONE"

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("confirmed")
    private  long confirmed;

    public DistCovids(String id, String name, long confirmed) {
        this.id = id;
        this.name = name;
        this.confirmed = confirmed;
    }

    public DistCovids() {

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getConfirmed() {
        return confirmed;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(DistCovids distCovids) {
        return this.name.compareTo(name);
    }
}
