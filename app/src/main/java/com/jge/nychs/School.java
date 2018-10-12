package com.jge.nychs;

import com.google.gson.annotations.SerializedName;

public class School {

    @SerializedName("dbn")
    private String dbn;
    @SerializedName("school_name")
    private String schoolName;

    @SerializedName("overview_paragraph")
    private String overview;

    @SerializedName("borough")
    private String borough;

    @SerializedName("school_email")
    private String schoolEmail;

    @SerializedName("phone_number")
    private String phoneNumber;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getSchoolEmail() {
        return schoolEmail;
    }

    public void setSchoolEmail(String schoolEmail) {
        this.schoolEmail = schoolEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getDbn() {
        return dbn;
    }

    public void setDbn(String dbn) {
        this.dbn = dbn;
    }
}
