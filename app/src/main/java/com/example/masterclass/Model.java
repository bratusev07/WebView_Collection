package com.example.masterclass;

public class Model {
    private int resId;
    private String link;

    public Model(int resId, String link) {
        this.resId = resId;
        this.link = link;
    }

    public int getResId() {
        return resId;
    }

    public String getLink() {
        return link;
    }
}
