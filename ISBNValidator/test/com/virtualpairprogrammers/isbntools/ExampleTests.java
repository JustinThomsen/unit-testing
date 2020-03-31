package com.virtualpairprogrammers.isbntools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExampleTests {

    @Test
    public void checkValidISBN(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449116");
        assertTrue(result, "first result");
        result = validator.checkISBN("0140177396");
        assertTrue(result, "second value");
    }

    @Test
    public void checkInvalidISBN(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449117");
        assertFalse(result);
    }

    @Test
    public void nineDigitNumberNotAllowed(){
        ValidateISBN validator = new ValidateISBN();

        assertThrows(NumberFormatException.class, () -> validator.checkISBN("123456789"));
    }

    @Test
    public void ISBNNumbersMustBeNumbers(){
        ValidateISBN validator = new ValidateISBN();

        assertThrows(NumberFormatException.class, () -> validator.checkISBN("helloworld"));

    }
}
