package org.example.utils;

import java.util.Random;


public class TestUtils {

    private final static Random random = new Random();

    public static String randomAccount() {
        String[] allAccounts = {"Revolut", "Cash", "CCB", "FIB"};

        int i = random.nextInt(allAccounts.length);
        return allAccounts[i];
    }

    public static String randomPayee() {
        String[] allPayees = {"Avanti", "Fantastiko", "Billa"};

        int i = random.nextInt(allPayees.length);
        return allPayees[i];
    }
}
