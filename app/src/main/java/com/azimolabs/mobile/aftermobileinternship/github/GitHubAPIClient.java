package com.azimolabs.mobile.aftermobileinternship.github;

import java.util.List;

import rx.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubAPIClient {
    @GET("users/{user}/repos")
    Observable<List<RepositoryItem>> loadRepos(@Path("user") String userName);
}
