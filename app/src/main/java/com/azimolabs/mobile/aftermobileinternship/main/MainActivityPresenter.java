package com.azimolabs.mobile.aftermobileinternship.main;

import com.azimolabs.mobile.aftermobileinternship.base.BasePresenter;
import com.azimolabs.mobile.aftermobileinternship.github.GitHubApiClient;
import com.azimolabs.mobile.aftermobileinternship.utils.Navigator;
import com.azimolabs.mobile.aftermobileinternship.utils.RxTransformerManager;

import javax.inject.Inject;

import retrofit2.HttpException;

import static com.azimolabs.mobile.aftermobileinternship.utils.ErrorType.CONNECTION_ERROR;
import static com.azimolabs.mobile.aftermobileinternship.utils.ErrorType.EMPTY_FIELD;
import static com.azimolabs.mobile.aftermobileinternship.utils.ErrorType.NO_REPOSITORIES;
import static com.azimolabs.mobile.aftermobileinternship.utils.ErrorType.UNKNOWN_USER;

class MainActivityPresenter extends BasePresenter {
    private final MainActivity view;
    private final GitHubApiClient gitHubAPIClient;
    private final Navigator navigator;
    private final RxTransformerManager rxTransformerManager;
    private final UserFieldErrorDisposer userFieldErrorDisposer;

    @Inject
    public MainActivityPresenter(
        MainActivity view,
        GitHubApiClient gitHubAPIClient,
        Navigator navigator,
        RxTransformerManager rxTransformerManager,
        UserFieldErrorDisposer userFieldErrorDisposer
    ) {
        this.view = view;
        this.gitHubAPIClient = gitHubAPIClient;
        this.navigator = navigator;
        this.rxTransformerManager = rxTransformerManager;
        this.userFieldErrorDisposer = userFieldErrorDisposer;
    }

    public void tryToLoadRepos(String name) {
        if (name.isEmpty()) {
            view.showError(userFieldErrorDisposer.getError(EMPTY_FIELD));
        } else {
            load(name);
        }
        view.hideKeyboard();
    }

    private void load(String name) {
        registerSubscription(
            gitHubAPIClient.loadRepos(name)
                .compose(rxTransformerManager::applyIOScheduler)
                .doOnSubscribe(view::showLoading)
                .doOnTerminate(view::hideLoading)
                .subscribe(
                    repositories -> {
                        if (repositories.isEmpty()) {
                            view.showError(userFieldErrorDisposer.getError(NO_REPOSITORIES));
                        } else {
                            navigator.navigateToReposDisplayActivity(name, repositories);
                        }
                    },
                    error -> {
                        if (error instanceof HttpException && ((HttpException) error).code() == 404) {
                            view.showError(userFieldErrorDisposer.getError(UNKNOWN_USER));
                        } else {
                            view.showError(userFieldErrorDisposer.getError(CONNECTION_ERROR));
                        }
                    }
                )
        );
    }

    public void textChanged() {
        view.hideError();
    }
}
