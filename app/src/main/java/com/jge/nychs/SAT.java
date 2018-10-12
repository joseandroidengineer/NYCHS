package com.jge.nychs;

import com.google.gson.annotations.SerializedName;

public class SAT {

    @SerializedName("num_of_sat_takers")
    private String numOfSATTakers;

    @SerializedName("sat_critical_reading_avg_score")
    private String satCriticalReadingAverageScore;

    @SerializedName("sat_math_avg_score")
    private String satMathAverageScore;

    @SerializedName("sat_writing_avg_score")
    private String satWritingAverageScore;

    @SerializedName("school_name")
    private String schoolName;

    public String getNumOfSATTakers() {
        return numOfSATTakers;
    }

    public void setNumOfSATTakers(String numOfSATTakers) {
        this.numOfSATTakers = numOfSATTakers;
    }

    public String getSatCriticalReadingAverageScore() {
        return satCriticalReadingAverageScore;
    }

    public void setSatCriticalReadingAverageScore(String satCriticalReadingAverageScore) {
        this.satCriticalReadingAverageScore = satCriticalReadingAverageScore;
    }

    public String getSatMathAverageScore() {
        return satMathAverageScore;
    }

    public void setSatMathAverageScore(String satMathAverageScore) {
        this.satMathAverageScore = satMathAverageScore;
    }

    public String getSatWritingAverageScore() {
        return satWritingAverageScore;
    }

    public void setSatWritingAverageScore(String satWritingAverageScore) {
        this.satWritingAverageScore = satWritingAverageScore;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
