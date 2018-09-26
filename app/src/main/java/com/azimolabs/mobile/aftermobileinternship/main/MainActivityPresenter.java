package com.azimolabs.mobile.aftermobileinternship.main;

import com.azimolabs.mobile.aftermobileinternship.base.BasePresenter;
import com.azimolabs.mobile.aftermobileinternship.github.GitHubAPIClient;
import com.azimolabs.mobile.aftermobileinternship.utils.Navigator;
import com.azimolabs.mobile.aftermobileinternship.utils.RxTransformerManager;

import javax.inject.Inject;

import static com.azimolabs.mobile.aftermobileinternship.utils.ErrorType.EMPTY_FIELD;
import static com.azimolabs.mobile.aftermobileinternship.utils.ErrorType.NO_REPOSITORIES;
import static com.azimolabs.mobile.aftermobileinternship.utils.ErrorType.UNKNOWN_USER;

class MainActivityPresenter extends BasePresenter {
    private final MainActivity view;
    private final GitHubAPIClient gitHubAPIClient;
    private final Navigator navigator;
    private final RxTransformerManager rxTransformerManager;

    @Inject
    public MainActivityPresenter(
        MainActivity view,
        GitHubAPIClient gitHubAPIClient,
        Navigator navigator,
        RxTransformerManager rxTransformerManager
    ) {
        this.view = view;
        this.gitHubAPIClient = gitHubAPIClient;
        this.navigator = navigator;
        this.rxTransformerManager = rxTransformerManager;
    }

    public void tryToLoadRepos(String name) {
        if (name.isEmpty()) {
            view.showError(EMPTY_FIELD);
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
                            view.showError(NO_REPOSITORIES);
                        } else {
                            navigator.navigateToReposDisplayActivity(name, repositories);
                        }
                    },
                    error -> view.showError(UNKNOWN_USER)
                )
        );
    }

    public void textChanged() {
        view.hideError();
    }
}
