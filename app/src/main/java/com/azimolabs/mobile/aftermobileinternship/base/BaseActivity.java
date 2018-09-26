package com.azimolabs.mobile.aftermobileinternship.base;

import android.support.v7.app.AppCompatActivity;

import com.azimolabs.mobile.aftermobileinternship.base.BasePresenter;
import com.azimolabs.mobile.aftermobileinternship.github.ActivityComponent;
import com.azimolabs.mobile.aftermobileinternship.GithubAppComponent;
import com.azimolabs.mobile.aftermobileinternship.GithubApplication;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void setContentView(int layoutResID) {
        onCreateComponent(GithubApplication.getComponent());
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    protected abstract ActivityComponent onCreateComponent(GithubAppComponent githubAppComponent);

    protected abstract BasePresenter getBasePresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getBasePresenter().unsubscribe();
    }
}