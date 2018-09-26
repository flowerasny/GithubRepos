package com.azimolabs.mobile.aftermobileinternship.utils;

import com.azimolabs.mobile.aftermobileinternship.base.BaseActivity;
import com.azimolabs.mobile.aftermobileinternship.github.RepositoryItem;
import com.azimolabs.mobile.aftermobileinternship.repositorieslist.RepositoriesListActivity;
import com.azimolabs.mobile.aftermobileinternship.repositorieslist.repositorydetails.RepositoryDetailsDialog;

import java.util.List;

import javax.inject.Inject;

public class Navigator {

    private final BaseActivity activity;

    @Inject
    public Navigator(BaseActivity activity) {
        this.activity = activity;
    }

    public void navigateToReposDisplayActivity(String userName, List<RepositoryItem> repositories){
        activity.startActivity(RepositoriesListActivity.intent(activity, userName, repositories));
    }

    public void openDetailsDialog(RepositoryItem repository){
        RepositoryDetailsDialog.newInstance(repository).show(activity.getSupportFragmentManager(), RepositoryDetailsDialog.TAG);
    }
}
