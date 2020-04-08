package com.virtualpairprogrammers.isbntools;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class StockManagementTests {

    @Test
    public void canGetACorrectLocatorCode(){
        ExternalISBNDataService testWebService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return new Book(isbn, "Of Mice And Men", "J. Steinbeck");
            }
        };
        ExternalISBNDataService testDBService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return null;
            }
        };
        StockManager stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDBService);

        String expectedLocatorCode = "7396J4";

        String isbn = "014017396";

        String actualLocatorCode = stockManager.getLocatorCode("0140177396");
        assertEquals(expectedLocatorCode, actualLocatorCode);
    }

    @Test
    public void databaseIsUsedIfDataIsPresentInDB(){
     fail();
    }

    @Test
    public void webserviceIsUsedIfDataIsNotPresentInDB(){
        fail();
    }

}
