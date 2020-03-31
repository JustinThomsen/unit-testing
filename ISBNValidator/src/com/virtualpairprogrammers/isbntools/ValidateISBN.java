package com.virtualpairprogrammers.isbntools;

public class ValidateISBN {
    public boolean checkISBN(String isbn) {
        if (isbn.length() != 10) {
            throw new NumberFormatException("ISBNs are 10 digits");
        }

        try {
            Integer.parseInt(isbn);
        } catch (Exception e){
            throw new NumberFormatException("ISBNs must be integers");
        }

        int total = 0;
        for (int i = 0; i < 10; i++) {
            total += isbn.charAt(i) * (10 - i);
        }

        if (total % 11 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
