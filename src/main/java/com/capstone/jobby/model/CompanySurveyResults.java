package com.capstone.jobby.model;

public class CompanySurveyResults {

    private int q1;
    private int q1W;
    private int q2;
    private int q2W;
    private int q3;
    private int q3W;
    private int q4;
    private int q4W;
    private int q5;
    private int q5W;
    private int q6;
    private int q6W;
    private int q7;
    private int q7W;
    private int q8;
    private int q8W;
    private int q9;
    private int q9W;
    private int q10;
    private int q10W;

    public void setQ1(int q1) {
        this.q1 = q1;
    }

    public void setQ1W(int q1W) {
        this.q1W = q1W;
    }

    public void setQ2(int q2) {
        this.q2 = q2;
    }

    public void setQ2W(int q2W) {
        this.q2W = q2W;
    }

    public void setQ3(int q3) {
        this.q3 = q3;
    }

    public void setQ3W(int q3W) {
        this.q3W = q3W;
    }

    public void setQ4(int q4) {
        this.q4 = q4;
    }

    public void setQ4W(int q4W) {
        this.q4W = q4W;
    }

    public void setQ5(int q5) {
        this.q5 = q5;
    }

    public void setQ5W(int q5W) {
        this.q5W = q5W;
    }

    public void setQ6(int q6) {
        this.q6 = q6;
    }

    public void setQ6W(int q6W) {
        this.q6W = q6W;
    }

    public void setQ7(int q7) {
        this.q7 = q7;
    }

    public void setQ7W(int q7W) {
        this.q7W = q7W;
    }

    public void setQ8(int q8) {
        this.q8 = q8;
    }

    public void setQ8W(int q8W) {
        this.q8W = q8W;
    }

    public void setQ9(int q9) {
        this.q9 = q9;
    }

    public void setQ9W(int q9W) {
        this.q9W = q9W;
    }

    public void setQ10(int q10) {
        this.q10 = q10;
    }

    public void setQ10W(int q10W) {
        this.q10W = q10W;
    }

    public Integer[] getAnswers(){
        Integer[] results = new Integer[]{q1,q2,q3,q4,q5,q6,q7,q8,q9,q10};
        return results;
    }
    public Integer[] getWeights(){
        Integer[] results = new Integer[]{q1W,q2W,q3W,q4W,q5W,q6W,q7W,q8W,q9W,q10W};
        return results;
    }
}
