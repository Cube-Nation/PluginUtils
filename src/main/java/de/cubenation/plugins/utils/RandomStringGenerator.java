package de.cubenation.plugins.utils;

import org.apache.commons.lang.RandomStringUtils;

public class RandomStringGenerator {
    private static final char[] symbols = new char[36];

    static {
        for (int idx = 0; idx < 10; ++idx) {
            symbols[idx] = (char) ('0' + idx);
        }
        for (int idx = 10; idx < 36; ++idx) {
            symbols[idx] = (char) ('a' + idx - 10);
        }
    }

    public static String nextString(int length) {
        return RandomStringUtils.random(length, 0, symbols.length, true, true, symbols);
    }
}
