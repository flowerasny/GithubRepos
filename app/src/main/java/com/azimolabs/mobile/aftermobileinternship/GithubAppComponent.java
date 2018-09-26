package com.azimolabs.mobile.aftermobileinternship;

import com.azimolabs.mobile.aftermobileinternship.GithubAppModule;
import com.azimolabs.mobile.aftermobileinternship.GithubApplication;
import com.azimolabs.mobile.aftermobileinternship.github.GitHubAPIClientModule;
import com.azimolabs.mobile.aftermobileinternship.main.MainActivityComponent;
import com.azimolabs.mobile.aftermobileinternship.repositorieslist.RepositoriesListActivity;
import com.azimolabs.mobile.aftermobileinternship.repositorieslist.RepositoriesListActivityComponent;

import dagger.Component;

@Component(
    modules = {
        GithubAppModule.class,
        GitHubAPIClientModule.class
    }
)
public interface GithubAppComponent {

    void inject(GithubApplication application);

    MainActivityComponent plus(MainActivityComponent.MainActivityModule mainActivityModule);

    RepositoriesListActivityComponent plus(RepositoriesListActivityComponent.RepositoriesListActivityModule repositoriesListActivityModule);
}
