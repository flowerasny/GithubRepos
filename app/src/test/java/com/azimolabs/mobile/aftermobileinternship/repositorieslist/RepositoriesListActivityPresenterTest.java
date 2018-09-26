package com.azimolabs.mobile.aftermobileinternship.repositorieslist;

import com.azimolabs.mobile.aftermobileinternship.github.RepositoryItem;
import com.azimolabs.mobile.aftermobileinternship.utils.Navigator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class RepositoriesListActivityPresenterTest {

    @Mock
    Navigator navigator;

    RepositoriesListActivityPresenter tested;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        tested = new RepositoriesListActivityPresenter(navigator);
    }

    @Test
    public void testRepositoryClicked_whenReposItemIsClicked_shouldOpenRepoDetailsDialog () {
        RepositoryItem repositoryMock = new RepositoryItem();

        tested.repositoryClicked(repositoryMock);

        verify(navigator).openDetailsDialog(repositoryMock);
    }
}