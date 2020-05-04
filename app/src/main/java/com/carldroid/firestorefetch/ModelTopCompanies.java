package com.carldroid.firestorefetch;

public class ModelTopCompanies {

    private String id;
    private String google;
    private String facebook;
    private String apple;
    private String uber;
    private String netflix;

    public ModelTopCompanies() {
    }

    public ModelTopCompanies(String id,String google, String facebook, String apple, String uber, String netflix) {
        this.id = id;
        this.google = google;
        this.facebook = facebook;
        this.apple = apple;
        this.uber = uber;
        this.netflix = netflix;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoogle() {
        return google;
    }

    public void setGoogle(String google) {
        this.google = google;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getApple() {
        return apple;
    }

    public void setApple(String apple) {
        this.apple = apple;
    }

    public String getUber() {
        return uber;
    }

    public void setUber(String uber) {
        this.uber = uber;
    }

    public String getNetflix() {
        return netflix;
    }

    public void setNetflix(String netflix) {
        this.netflix = netflix;
    }
}
