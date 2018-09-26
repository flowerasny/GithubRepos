package com.azimolabs.mobile.aftermobileinternship.main;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.azimolabs.mobile.aftermobileinternship.GithubAppComponent;
import com.azimolabs.mobile.aftermobileinternship.R;
import com.azimolabs.mobile.aftermobileinternship.base.BaseActivity;
import com.azimolabs.mobile.aftermobileinternship.base.BasePresenter;
import com.azimolabs.mobile.aftermobileinternship.github.ActivityComponent;
import com.azimolabs.mobile.aftermobileinternship.utils.ErrorType;
import com.azimolabs.mobile.aftermobileinternship.utils.KeyboardHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends BaseActivity {

    @BindView(R.id.pbLoadingRepos)
    ProgressBar pbLoadingRepos;
    @BindView(R.id.etUserName)
    EditText editText;
    @BindView(R.id.tvUserError)
    TextView tvUserError;

    @Inject
    MainActivityPresenter presenter;
    @Inject
    KeyboardHelper keyboardHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick(R.id.btnLoadRepos)
    public void clicked() {
        presenter.tryToLoadRepos(editText.getText().toString());
    }

    @Override
    protected ActivityComponent onCreateComponent(GithubAppComponent githubAppComponent) {
        MainActivityComponent component = githubAppComponent.plus(new MainActivityComponent.MainActivityModule(this));
        component.inject(this);
        return component;
    }

    @OnTextChanged(R.id.etUserName)
    public void textChanged() {
        presenter.textChanged();
    }

    @Override
    protected BasePresenter getBasePresenter() {
        return presenter;
    }

    public void showError(ErrorType type) {
        switch (type) {
            case EMPTY_FIELD:
                tvUserError.setText(R.string.please_fill_in);
                break;
            case UNKNOWN_USER:
                tvUserError.setText(R.string.user_doesn_t_exist);
                break;
            case NO_REPOSITORIES:
                tvUserError.setText(R.string.user_has_no_repositories);
        }
        tvUserError.setVisibility(View.VISIBLE);
        editText.getBackground().mutate().setColorFilter(getResources().getColor(R.color.error), PorterDuff.Mode.SRC_ATOP);
    }

    public void hideError() {
        tvUserError.setVisibility(View.INVISIBLE);
        editText.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
    }

    public void showLoading() {
        pbLoadingRepos.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        pbLoadingRepos.setVisibility(View.INVISIBLE);
    }

    public void hideKeyboard() {
        keyboardHelper.hideKeyboard();
    }

}
