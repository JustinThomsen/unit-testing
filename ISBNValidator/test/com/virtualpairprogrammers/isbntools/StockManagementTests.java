package com.virtualpairprogrammers.isbntools;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;

public class StockManagementTests {
    StockManager stockManager = null;
    ExternalISBNDataService webService = null;
    ExternalISBNDataService dbService = null;
    String isbn = "0140177396";

    @BeforeEach
    public void before(){
        stockManager = new StockManager();
        webService = mock(ExternalISBNDataService.class);
        dbService = mock(ExternalISBNDataService.class);
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(dbService);
    }

    @Test
    public void canGetACorrectLocatorCode(){
        when(webService.lookup(isbn)).thenReturn(new Book(isbn, "Of Mice And Men", "J. Steinbeck"));
        when(dbService.lookup(isbn)).thenReturn(null);
        String expectedLocatorCode = "7396J4";

        String actualLocatorCode = stockManager.getLocatorCode("0140177396");

        assertEquals(expectedLocatorCode, actualLocatorCode);
    }

    @Test
    public void webserviceIsUsedIfDataIsNotPresentInDB(){
        when(dbService.lookup(isbn)).thenReturn(null);
        when(webService.lookup(isbn)).thenReturn(new Book(isbn, "foo", "bar"));

        stockManager.getLocatorCode(isbn);

        verify(dbService).lookup(isbn);
        verify(webService).lookup(isbn);
    }

    @Test
    public void databaseIsUsedIfDataIsPresentInDB(){
        when(dbService.lookup(isbn)).thenReturn(new Book(isbn, "foo", "bar"));

        stockManager.getLocatorCode(isbn);

        verify(dbService).lookup(isbn);
        verify(webService, never()).lookup(anyString());
    }

}
