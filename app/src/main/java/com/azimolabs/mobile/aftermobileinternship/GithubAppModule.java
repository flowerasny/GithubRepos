package com.azimolabs.mobile.aftermobileinternship;

import android.content.Context;

import com.azimolabs.mobile.aftermobileinternship.GithubApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class GithubAppModule {

    private final GithubApplication application;

    public GithubAppModule(GithubApplication application) {
        this.application = application;
    }

    @Provides
    public Context provideContext() {
        return application;
    }
}