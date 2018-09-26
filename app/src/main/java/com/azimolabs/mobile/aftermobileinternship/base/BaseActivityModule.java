package com.azimolabs.mobile.aftermobileinternship.base;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class BaseActivityModule<T extends BaseActivity> {
    protected final T activity;

    public BaseActivityModule(T activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    public T provideActivity(){return activity;}

    @Provides
    @Singleton
    public BaseActivity provideBaseActivity(){return activity;}
}
