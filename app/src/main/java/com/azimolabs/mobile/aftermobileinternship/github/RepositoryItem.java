package com.azimolabs.mobile.aftermobileinternship.github;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RepositoryItem implements Serializable {
    private String name;
    private String description;
    private String language;
    @SerializedName("open_issues_count")
    private int issues;
    @SerializedName("stargazers_count")
    private int stars;
    private int forks;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public int getIssues() {
        return issues;
    }

    public int getStars() {
        return stars;
    }

    public int getForks() {
        return forks;
    }
}
