package com.azimolabs.mobile.aftermobileinternship.github;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GitHubApiClient {
    @GET("users/{user}/repos")
    Observable<List<RepositoryItem>> loadRepos(@Path("user") String userName);
}
