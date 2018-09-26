package com.azimolabs.mobile.aftermobileinternship.utils;

public class RepositoryDetailsFormatter {

    public static String repoDescription(String description) {
        if (description == null || description.isEmpty()){
            return "No description";
        } else {
            return description;
        }
    }

    public static String repoLanguage(String language) {
        if (language == null || language.isEmpty()){
            return "Language not specified";
        } else {
            return language;
        }
    }
}
