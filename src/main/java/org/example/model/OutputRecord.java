package org.example.model;

import static org.example.config.Config.DELIMITER;

public class OutputRecord {

    private String data;
    private double expense;
    private String account; // Revolut
    private String payee; // Avanti
    private double volume;
    private String kind;

    public OutputRecord() {}

    public OutputRecord(String data, double expense, String account, String payee, double volume, String kind) {
        this.data = data;
        this.expense = expense;
        this.account = account;
        this.payee = payee;
        this.volume = volume;
        this.kind = kind;
    }

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

    public String toCsv() {
        StringBuilder sb = new StringBuilder();
        return sb.append(this.data).append(DELIMITER)
                .append(this.expense).append(DELIMITER)
                .append(this.account).append(DELIMITER)
                .append(this.payee).append(DELIMITER)
                .append(this.volume).append(DELIMITER)
                .append(this.kind)
                .append(System.lineSeparator())
                .toString();
    }
}
