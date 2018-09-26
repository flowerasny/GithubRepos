package com.azimolabs.mobile.aftermobileinternship.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class RepositoryDetailsFormatterTest {

    @Test
    public void testRepoDescription(){
        String description = "description";
        assertEquals(RepositoryDetailsFormatter.repoDescription(description), description);
    }

    @Test
    public void testRepoDescription2(){
        String description = "";
        assertEquals(RepositoryDetailsFormatter.repoDescription(description), "No description");
    }

    @Test
    public void testRepoLanguage(){
        String description = "language";
        assertEquals(RepositoryDetailsFormatter.repoLanguage(description), description);
    }

    @Test
    public void testRepoLanguage2(){
        String description = "";
        assertEquals(RepositoryDetailsFormatter.repoLanguage(description), "Language not specified");
    }

}