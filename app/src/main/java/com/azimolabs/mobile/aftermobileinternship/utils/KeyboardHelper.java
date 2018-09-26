package com.azimolabs.mobile.aftermobileinternship.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import com.azimolabs.mobile.aftermobileinternship.base.BaseActivity;

import javax.inject.Inject;

public class KeyboardHelper {

    private final BaseActivity activity;
    private final InputMethodManager inputMethodManager;

    @Inject
    public KeyboardHelper(BaseActivity activity) {
        this.activity = activity;
        this.inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    public void hideKeyboard(){
        inputMethodManager.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
    }
}
