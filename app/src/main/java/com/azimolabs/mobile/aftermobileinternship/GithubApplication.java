package com.azimolabs.mobile.aftermobileinternship;

import android.app.Application;

public class GithubApplication extends Application {

    private static GithubAppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerGithubAppComponent.builder().build();
        component.inject(this);
    }

    public static GithubAppComponent getComponent() {
        return component;
    }
}
