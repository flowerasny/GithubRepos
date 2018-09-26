package com.azimolabs.mobile.aftermobileinternship.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter {
    private final CompositeSubscription subscriptions = new CompositeSubscription();

    public void registerSubscription(Subscription subscription){
        subscriptions.add(subscription);
    }

    public void unsubscribe(){
        subscriptions.clear();
    }
}
