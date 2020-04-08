package com.virtualpairprogrammers.isbntools;

public class ValidateISBN {
    public static final int ISBN_SHORT_MULTIPLIER = 11;
    public static final int ISBN_LONG_MULTIPLIER = 10;
    public static final int X_DIGIT_VALUE = 10;
    public static final char LAST_SPECIAL_CHARACTER = 'X';
    public static final int ODD_NUMBER_MULTIPLIER = 3;
    public static final int EVEN_NUMBER_MULTIPLIER = 1;
    final int ISBN_SHORT = 10;
    final int ISBN_LONG = 13;

    public boolean checkISBN(String isbn) {
        isbn = isbn.replace("-", "");
        int total = 0;
        checkISBNFormat(isbn);
        switch (isbn.length()) {
            case ISBN_SHORT:
                total = getTotalForShortISBN(isbn);
                return total % ISBN_SHORT_MULTIPLIER == 0;
            case ISBN_LONG:
                total = getTotalForLongISBN(isbn);
                return total % ISBN_LONG_MULTIPLIER == 0;
            default:
                throw new NumberFormatException("ISBNs are 10 or 13 digits");
        }
    }

    private int getTotalForShortISBN(String isbn) {
        int total = 0;
        char isbnDigit;

        for (int i = 0; i < ISBN_SHORT; i++) {
            isbnDigit = isbn.charAt(i);
            if (i == isbn.length() - 1 && isbnDigit == LAST_SPECIAL_CHARACTER) {
                isbnDigit = X_DIGIT_VALUE;
            }
            int amtToMultiply = 10 - i;
            total = total + Character.getNumericValue(isbnDigit) * amtToMultiply;
        }
        return total;
    }

    private int getTotalForLongISBN(String isbn) {
        int total = 0;

        char isbnDigit;
        for (int i = 0; i < ISBN_LONG; i++) {
            int amtToMultiply = ODD_NUMBER_MULTIPLIER;
            isbnDigit = isbn.charAt(i);

            if (i % 2 == 0) {
                amtToMultiply = EVEN_NUMBER_MULTIPLIER;
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
            if (!Character.isDigit(isbnDigit) && isbnDigit != LAST_SPECIAL_CHARACTER) {
                throw new NumberFormatException("ISBN last digit must be an integer or " + LAST_SPECIAL_CHARACTER);
            }
        }
    }
}
