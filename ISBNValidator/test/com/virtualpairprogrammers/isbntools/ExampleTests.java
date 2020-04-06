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
        assertTrue(result, "second result");
    }

    @Test
    public void checkValidISBNEndingInX() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("012000030X");
        assertTrue(result);
    }

    @Test
    public void checkInvalid10DigitISBN(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449117");
        assertFalse(result);
    }
    @Test
    public void checkInvalid13DigitISBN(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("978-0756404075");
        assertFalse(result);
    }

    @Test
    public void isbnShouldBe10or13Digits(){
        ValidateISBN validator = new ValidateISBN();

        assertThrows(NumberFormatException.class, () -> validator.checkISBN("123456789"));
    }

    @Test
    public void ISBNDigitsMustBeNumbers(){
        ValidateISBN validator = new ValidateISBN();

        assertThrows(NumberFormatException.class, () -> validator.checkISBN("hell0world"));

    }

    @Test
    public void ISBNOf13DigitsShouldBeValid(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("978-0756404079");
        assertTrue(result);
    }
}
