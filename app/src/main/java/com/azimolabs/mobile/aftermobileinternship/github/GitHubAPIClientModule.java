package com.azimolabs.mobile.aftermobileinternship.github;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class GitHubAPIClientModule {

    @Provides
    public GitHubAPIClient gitHubAPIClient(Retrofit retrofit) {
        return retrofit.create(GitHubAPIClient.class);
    }

    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.github.com/")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(gsonConverterFactory)
            .build();
    }

    @Provides
    public GsonConverterFactory gsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder().build();
    }

}
