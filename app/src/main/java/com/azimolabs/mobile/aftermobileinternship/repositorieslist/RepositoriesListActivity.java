package com.azimolabs.mobile.aftermobileinternship.repositorieslist;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.azimolabs.mobile.aftermobileinternship.GithubAppComponent;
import com.azimolabs.mobile.aftermobileinternship.base.BaseActivity;
import com.azimolabs.mobile.aftermobileinternship.base.BasePresenter;
import com.azimolabs.mobile.aftermobileinternship.github.ActivityComponent;
import com.azimolabs.mobile.aftermobileinternship.github.RepositoryItem;
import com.azimolabs.mobile.aftermobileinternship.GithubAppComponent;
import com.azimolabs.mobile.aftermobileinternship.R;
import com.azimolabs.mobile.aftermobileinternship.base.BaseActivity;
import com.azimolabs.mobile.aftermobileinternship.base.BasePresenter;
import com.azimolabs.mobile.aftermobileinternship.github.ActivityComponent;
import com.azimolabs.mobile.aftermobileinternship.github.RepositoryItem;
import com.azimolabs.mobile.aftermobileinternship.repositorieslist.RepositoriesListActivityComponent;
import com.azimolabs.mobile.aftermobileinternship.repositorieslist.RepositoriesListActivityPresenter;
import com.azimolabs.mobile.aftermobileinternship.repositorieslist.RepositoriesListAdapter;
import com.azimolabs.mobile.aftermobileinternship.repositorieslist.repositorydetails.RepositoryDetailsDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;

public class RepositoriesListActivity extends BaseActivity implements RepositoriesListAdapter.OnRepositoryItemClickListener{
    private static final String USER_REPOSITORIES = "user_repositories";
    private static final String USER_NAME = "user_name";

    @BindView(R.id.rvRepositoriesList)
    RecyclerView rvRepositoriesList;

    @Inject
    RepositoriesListActivityPresenter presenter;
    @Inject
    RepositoriesListAdapter adapter;

    public static Intent intent(BaseActivity baseActivity, String userName, List<RepositoryItem> repositories){
        return new Intent(baseActivity, RepositoriesListActivity.class)
            .putExtra(USER_NAME, userName)
            .putExtra(USER_REPOSITORIES, (Serializable) repositories);
    }

    private String userName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories_list);

        getSupportActionBar().setTitle(userName);
        setupRepositoryList();
    }

    private void setupRepositoryList() {
        rvRepositoriesList.setLayoutManager(new LinearLayoutManager(this));
        rvRepositoriesList.setHasFixedSize(true);
        rvRepositoriesList.setAdapter(adapter);
    }

    @Override
    protected ActivityComponent onCreateComponent(GithubAppComponent githubAppComponent) {
        userName = getIntent().getStringExtra(USER_NAME);
        List<RepositoryItem> repos = (List<RepositoryItem>) getIntent().getSerializableExtra(USER_REPOSITORIES);
        RepositoriesListActivityComponent component = githubAppComponent.plus(new RepositoriesListActivityComponent.RepositoriesListActivityModule(this, repos));
        component.inject(this);
        return component;
    }

    @Override
    protected BasePresenter getBasePresenter() {
        return presenter;
    }

    @Override
    public void onItemClicked(RepositoryItem repository) {
        presenter.repositoryClicked(repository);
    }
}
