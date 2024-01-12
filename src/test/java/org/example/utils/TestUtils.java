package org.example.utils;

import org.example.model.Account;

import java.util.Random;


public class TestUtils {

    private final static Random random = new Random();

    public static String randomAccount() {
        Account[] allAccounts = {Account.REVOLUT, Account.CASH, Account.CCB, Account.FIB};

        int i = random.nextInt(allAccounts.length);
        return allAccounts[i].toString();
    }

    public static String randomPayee() {
        String[] allPayees = {"Avanti", "Fantastiko", "Billa"};

        int i = random.nextInt(allPayees.length);
        return allPayees[i];
    }
}
