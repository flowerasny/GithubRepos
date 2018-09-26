package com.azimolabs.mobile.aftermobileinternship.utils;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Singleton
public class RxTransformerManager {

    @Inject
    public RxTransformerManager() {
    }

    public <T> Observable<T> applyIOScheduler(Observable<T> observable) {
        return observable.subscribeOn(schedulerIo())
            .observeOn(schedulerUi());
    }

    public Scheduler schedulerIo() {
        return Schedulers.io();
    }

    public Scheduler schedulerUi() {
        return AndroidSchedulers.mainThread();
    }
}
