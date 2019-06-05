package br.com.dimens.tictactoe.utils;

public class StringUtilities {
    private static final String EMPTY_STRING = "";
    private static final StringUtilities STRING_UTILITIES_INSTANCE = new StringUtilities();

    private StringUtilities() { }

    public static boolean isEmptyOrNull(String value) {
        return value == null || value.equals(EMPTY_STRING);
    }

    public static String stringFromNumbers(int... numbers) {
        StringBuilder sNumbers = new StringBuilder();

        for (int number: numbers) {
            sNumbers.append(number);
        }

        return sNumbers.toString();
    }
}
