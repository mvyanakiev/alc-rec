package org.example.model;

import static org.example.config.Config.DELIMITER;

public class OutputRecord {

    public OutputRecord(String data, double expense, Account account, String payee, String originalNote, double volume, String kind, int points) {
        this.data = data;
        this.expense = expense;
        this.account = account;
        this.payee = payee;
        this.originalNote = originalNote;
        this.volume = volume;
        this.kind = kind;
        this.points = points;
    }

    private String data;
    private double expense;
    private Account account; // Revolut
    private String payee; // Avanti
    private String originalNote;
    private double volume;
    private String kind;
    private int points;

    public OutputRecord() {}

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String toCsv() {
        StringBuilder sb = new StringBuilder();
        return sb.append(this.data).append(DELIMITER)
                .append(this.expense).append(DELIMITER)
                .append(this.account).append(DELIMITER)
                .append(this.payee).append(DELIMITER)
                .append(this.originalNote).append(DELIMITER)
                .append(this.volume).append(DELIMITER)
                .append(this.kind).append(DELIMITER)
                .append(this.points)
//                .append(System.lineSeparator())
                .toString();
    }
}
