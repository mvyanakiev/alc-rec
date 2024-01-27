package org.example.model;

import java.util.Map;

public class OutputRecord {

    private int id;
    private String data;
    private double expense;
    private Account account;
    private String payee;
    private String originalNote;
    private double volume;
    private String kind;
    private int points;
    private Map<String, Integer> pointProducers;

    public OutputRecord() {}

    public OutputRecord(int id, String data, double expense, Account account, String payee, String originalNote,
                        double volume, String kind, int points, Map<String, Integer> pointProducers) {
        this.id = id;
        this.data = data;
        this.expense = expense;
        this.account = account;
        this.payee = payee;
        this.originalNote = originalNote;
        this.volume = volume;
        this.kind = kind;
        this.points = points;
        this.pointProducers = pointProducers;
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

    public String getOriginalNote() {
        return originalNote;
    }

    public void setOriginalNote(String originalNote) {
        this.originalNote = originalNote;
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

    public Map<String, Integer> getPointProducers() {
        return pointProducers;
    }

    public void setPointProducers(Map<String, Integer> pointProducers) {
        this.pointProducers = pointProducers;
    }
}
