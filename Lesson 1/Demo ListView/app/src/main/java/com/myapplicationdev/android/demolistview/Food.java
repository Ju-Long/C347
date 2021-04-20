package com.myapplicationdev.android.demolistview;

public class Food {
    private String name;
    private boolean star;

    public Food(String name, boolean star) {
        this.name = name;
        this.star = star;
    }

    public String getName() {
        return name;
    }

    public boolean isStar() {
        return star;
    }
}
