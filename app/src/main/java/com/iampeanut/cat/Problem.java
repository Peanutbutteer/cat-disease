package com.iampeanut.cat;

import org.parceler.Parcel;

/**
 * Created by satjawatpanakarn on 5/2/2017 AD.
 */
@Parcel
public class Problem {
    private String key;
    private String name;
    private String value = "yes";
    private int icon;
    private boolean problem = false;

    public Problem() {

    }

    public Problem(String key, String name, int icon) {
        this.key = key;
        this.name = name;
        this.icon = icon;
    }

    public Problem(String key, String name, String value, int icon) {
        this.key = key;
        this.name = name;
        this.value = value;
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isProblem() {
        return problem;
    }

    public void setProblem(boolean problem) {
        this.problem = problem;
    }

    public String getValue() {
        return value;
    }
}
