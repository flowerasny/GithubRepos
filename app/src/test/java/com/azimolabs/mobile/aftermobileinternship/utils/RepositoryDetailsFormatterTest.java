package com.azimolabs.mobile.aftermobileinternship.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RepositoryDetailsFormatterTest {

    @Test
    public void testRepoDescription_whenDescriptionIsNotEmpty_shouldReturnDescription() {
        String description = "description";

        assertEquals(RepositoryDetailsFormatter.repoDescription(description), description);
    }

    @Test
    public void testRepoDescription_whenDescriptionIsEmpty_shouldReturnNoDescription() {
        String description = "";
        assertEquals(RepositoryDetailsFormatter.repoDescription(description), "No description");
    }

    @Test
    public void testRepoLanguage_whenLanguageIsNotEmpty_shouldReturnLanguage() {
        String description = "language";
        assertEquals(RepositoryDetailsFormatter.repoLanguage(description), description);
    }

    @Test
    public void testRepoLanguage_whenLanguageIsEmpty_shouldReturnLanguageNotSpecified() {
        String description = "";
        assertEquals(RepositoryDetailsFormatter.repoLanguage(description), "Language not specified");
    }

}