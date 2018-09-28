package com.azimolabs.mobile.aftermobileinternship.main;

import android.accounts.NetworkErrorException;

import com.azimolabs.mobile.aftermobileinternship.github.GitHubApiClient;
import com.azimolabs.mobile.aftermobileinternship.github.RepositoryItem;
import com.azimolabs.mobile.aftermobileinternship.utils.ErrorType;
import com.azimolabs.mobile.aftermobileinternship.utils.Navigator;
import com.azimolabs.mobile.aftermobileinternship.utils.RxTransformerManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import rx.Observable;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainActivityPresenterTest {
    @Mock
    MainActivity view;
    @Mock
    GitHubApiClient gitHubAPIClient;
    @Mock
    Navigator navigator;
    @Mock
    RxTransformerManager rxTransformerManager;

    MainActivityPresenter tested;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        tested = new MainActivityPresenter(
          view,
          gitHubAPIClient,
          navigator,
          rxTransformerManager
        );
    }

    @Test
    public void testTryToLoadRepos_whenConnectionFailed_shouldShowUnknownUserErrorAndHideKeyboard() {
        String userNameMock = "user";
        Observable<List<RepositoryItem>> observableMock = Observable.error(new NetworkErrorException());
        when(gitHubAPIClient.loadRepos(userNameMock)).thenReturn(observableMock);
        when(rxTransformerManager.applyIOScheduler(observableMock)).thenReturn(observableMock);

        tested.tryToLoadRepos(userNameMock);

        verify(view).hideKeyboard();
        InOrder inOrder = inOrder(view);
        inOrder.verify(view).showLoading();
        inOrder.verify(view).hideLoading();
        verify(view).showError(ErrorType.UNKNOWN_USER);
    }

    @Test
    public void testTryToLoadRepos_whenUserHasNoRepos_shouldShowNoReposErrorAndHideKeyboard() {
        String userNameMock = "user";
        List<RepositoryItem> userReposMock = Collections.emptyList();
        Observable<List<RepositoryItem>> observableMock = Observable.just(userReposMock);
        when(gitHubAPIClient.loadRepos(userNameMock)).thenReturn(observableMock);
        when(rxTransformerManager.applyIOScheduler(observableMock)).thenReturn(observableMock);

        tested.tryToLoadRepos(userNameMock);

        verify(view).hideKeyboard();
        InOrder inOrder = inOrder(view);
        inOrder.verify(view).showLoading();
        inOrder.verify(view).hideLoading();
        verify(view).showError(ErrorType.NO_REPOSITORIES);
    }

    @Test
    public void testTryToLoadRepos_whenUserHasRepos_shouldNavigateToReposDisplayAndHideKeyboard() {
        String userNameMock = "user";
        List<RepositoryItem> userReposMock = Collections.singletonList(new RepositoryItem());
        Observable<List<RepositoryItem>> observableMock = Observable.just(userReposMock);
        when(gitHubAPIClient.loadRepos(userNameMock)).thenReturn(observableMock);
        when(rxTransformerManager.applyIOScheduler(observableMock)).thenReturn(observableMock);

        tested.tryToLoadRepos(userNameMock);

        verify(view).hideKeyboard();
        InOrder inOrder = inOrder(view);
        inOrder.verify(view).showLoading();
        inOrder.verify(view).hideLoading();
        verify(navigator).navigateToReposDisplayActivity(userNameMock, userReposMock);
    }

    @Test
    public void testTryToLoadRepos_whenNameIsEmpty_shouldShowEmptyFieldErrorAndHideKeyboard() {
        tested.tryToLoadRepos("");

        verify(view).showError(ErrorType.EMPTY_FIELD);
        verify(view).hideKeyboard();
    }

    @Test
    public void testTextChanged() {
        tested.textChanged();

        verify(view).hideError();
    }
}