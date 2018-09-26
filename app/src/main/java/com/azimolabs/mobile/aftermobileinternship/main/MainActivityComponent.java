package com.azimolabs.mobile.aftermobileinternship.main;

import com.azimolabs.mobile.aftermobileinternship.github.ActivityComponent;
import com.azimolabs.mobile.aftermobileinternship.base.BaseActivityModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Subcomponent;

@Singleton
@Subcomponent(
    modules = MainActivityComponent.MainActivityModule.class
)
public interface MainActivityComponent extends ActivityComponent {
    MainActivity inject(MainActivity activity);

    @Module
    class MainActivityModule extends BaseActivityModule<MainActivity> {

        public MainActivityModule(MainActivity activity) {
            super(activity);
        }
    }
}