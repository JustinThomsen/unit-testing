package com.virtualpairprogrammers.isbntools;

public class ValidateISBN {
    public boolean checkISBN(String isbn) {
        isbn = isbn.replace("-", "");
        int total = 0;
        checkISBNFormat(isbn);
        switch (isbn.length()) {
            case 10:
                total = getTotalFor10DigitISBN(isbn);
                break;
            case 13:
                total = getTotalFor13DigitISBN(isbn);
                break;
            default:
                throw new NumberFormatException("ISBNs are 10 or 13 digits");
        }

        switch (isbn.length()) {
            case 10:
                return total % 11 == 0;
            case 13:
                return total % 10 == 0;
            default:
                System.out.println("she's dead jim");
                return false;
        }
    }

    private int getTotalFor10DigitISBN(String isbn) {
        int total = 0;
        char isbnDigit;

        for (int i = 0; i < 10; i++) {
            isbnDigit = isbn.charAt(i);
            if (i == isbn.length() - 1 && isbnDigit == 'X') {
                isbnDigit = 10;
            }
            int amtToMultiply = 10 - i;
            total = total + Character.getNumericValue(isbnDigit) * amtToMultiply;
        }
        return total;
    }

    private int getTotalFor13DigitISBN(String isbn) {
        int total = 0;
        int amtToMultiply;
        char isbnDigit;

        for (int i = 0; i < 13; i++) {
            isbnDigit = isbn.charAt(i);
            if ((i == isbn.length() - 1) && (isbnDigit == 'X')) {
                isbnDigit = 10;
            }
            if (i % 2 == 0) {
                amtToMultiply = 1;
            } else {
                amtToMultiply = 3;
            }
            total = total + Character.getNumericValue(isbnDigit) * amtToMultiply;
        }
        return total;
    }

    private void checkISBNFormat(String isbn) {
        for (int i = 0; i < isbn.length(); i++) {
            char isbnDigit = isbn.charAt(i);
            if (i != isbn.length() - 1 && !Character.isDigit(isbnDigit)) {
                throw new NumberFormatException("ISBN digits must be integers");
            }
            if (!Character.isDigit(isbnDigit) && isbnDigit != 'X') {
                throw new NumberFormatException("ISBN last digit must be an integer or X");
            }
        }
    }
}
