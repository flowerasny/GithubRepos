package com.azimolabs.mobile.aftermobileinternship.repositorieslist;

import com.azimolabs.mobile.aftermobileinternship.base.BaseActivityModule;
import com.azimolabs.mobile.aftermobileinternship.github.ActivityComponent;
import com.azimolabs.mobile.aftermobileinternship.github.RepositoryItem;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

@Singleton
@Subcomponent(
    modules = RepositoriesListActivityComponent.RepositoriesListActivityModule.class
)
public interface RepositoriesListActivityComponent extends ActivityComponent {
    RepositoriesListActivity inject(RepositoriesListActivity activity);

    @Module
    class RepositoriesListActivityModule extends BaseActivityModule<RepositoriesListActivity> {
        List<RepositoryItem> repositories;

        public RepositoriesListActivityModule(RepositoriesListActivity activity, List<RepositoryItem> repositories) {
            super(activity);
            this.repositories = repositories;
        }

        @Provides
        public List<RepositoryItem> provideRepositories(){
            return repositories;
        }
    }
}