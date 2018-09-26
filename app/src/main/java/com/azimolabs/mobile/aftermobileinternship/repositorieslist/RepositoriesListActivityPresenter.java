package com.azimolabs.mobile.aftermobileinternship.repositorieslist;

import com.azimolabs.mobile.aftermobileinternship.base.BasePresenter;
import com.azimolabs.mobile.aftermobileinternship.github.RepositoryItem;
import com.azimolabs.mobile.aftermobileinternship.utils.Navigator;

import javax.inject.Inject;

public class RepositoriesListActivityPresenter extends BasePresenter {

    private final Navigator navigator;

    @Inject
    public RepositoriesListActivityPresenter(Navigator navigator) {
        this.navigator = navigator;
    }

    public void repositoryClicked(RepositoryItem repository) {
        navigator.openDetailsDialog(repository);
    }
}
