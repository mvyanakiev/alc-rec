package org.example.model;

public class InputRecord {

    private String data;
    private String category;
    private String subCategory;
    private double expense;
    private Account account; // Revolut
    private String payee; // Avanti
    private String notes;

        public InputRecord(String data, String category, String subCategory, double expense, String account, String payee, String notes) {
        this.data = data;
        this.category = category;
        this.subCategory = subCategory;
        this.expense = expense;
        setAccount(account);
        this.payee = payee;
        this.notes = notes;
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

        switch (inputType.toUpperCase()) {
            case "REVOLUT":
                this.account = Account.REVOLUT;
                break;
            case "CASH":
                this.account = Account.CASH;
                break;
            case "CCB":
                this.account = Account.CCB;
                break;
            case "FIB":
                this.account = Account.FIB;
                break;

            default:
                throw new IllegalArgumentException("Invalid Account type");
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
