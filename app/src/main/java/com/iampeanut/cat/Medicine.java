package com.iampeanut.cat;

/**
 * Created by satjawatpanakarn on 5/3/2017 AD.
 */

public class Medicine {
    String name;
    Double price;

    public Medicine(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}
