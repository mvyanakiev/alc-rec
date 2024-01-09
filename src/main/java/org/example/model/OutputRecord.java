package org.example.model;

public class OutputRecord {

    private String data;
    private double expense;
    private String account; // Revolut
    private String payee; // Avanti
    private double volume;
    private String kind;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
