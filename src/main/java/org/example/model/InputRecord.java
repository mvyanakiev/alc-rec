package org.example.model;

import java.util.Map;

public class InputRecord {

    private int id;
    private String data;
    private String category;
    private String subCategory;
    private double expense;
    private Account account; // Revolut
    private String payee; // Avanti
    private String notes;

    public InputRecord() {}

    public InputRecord(int id, String data, String category, String subCategory, double expense,
                       String account, String payee, String notes) {
        this.id = id;
        this.data = data;
        this.category = category;
        this.subCategory = subCategory;
        this.expense = expense;
        setAccount(account);
        this.payee = payee;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setAccount(String inputType) {
        if (inputType == null || inputType.isEmpty()) {
            throw new NullPointerException("Invalid Account");
        }

        switch (inputType) {
            case "Revolut":
                this.account = Account.REVOLUT;
                break;
            case "Cash":
                this.account = Account.CASH;
                break;
            case "ЦКБ":
                this.account = Account.CCB;
                break;
            case "ПИБ":
                this.account = Account.FIB;
                break;
            case "Спестовна":
                this.account = Account.SAVING;
                break;
            case "Почивка":
                this.account = Account.LEISURE;
                break;
            case "Поддръжка кола":
                this.account = Account.CAR_SUPPORT;
                break;

            default:
                throw new IllegalArgumentException("Invalid Account type: " + inputType);
        }
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
