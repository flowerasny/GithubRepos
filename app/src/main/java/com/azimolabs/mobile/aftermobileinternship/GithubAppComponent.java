package com.azimolabs.mobile.aftermobileinternship;

import com.azimolabs.mobile.aftermobileinternship.github.GitHubApiClientModule;
import com.azimolabs.mobile.aftermobileinternship.main.MainActivityComponent;
import com.azimolabs.mobile.aftermobileinternship.repositorieslist.RepositoriesListActivityComponent;

import dagger.Component;

@Component(
    modules = {
        GithubAppModule.class,
        GitHubApiClientModule.class
    }
)
public interface GithubAppComponent {

    void inject(GithubApplication application);

    MainActivityComponent plus(MainActivityComponent.MainActivityModule mainActivityModule);

    RepositoriesListActivityComponent plus(RepositoriesListActivityComponent.RepositoriesListActivityModule repositoriesListActivityModule);
}
