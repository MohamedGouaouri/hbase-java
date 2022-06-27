package models;

import com.google.gson.annotations.SerializedName;

public class GeneralInfo {
    public double speed;
    public double lat;
    @SerializedName(value = "long")
    public double long_;
}
