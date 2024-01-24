package org.example.model;

public class SummarisedElement {

    private double volume;
    private double expense;

    public SummarisedElement() {}

    public SummarisedElement(double volume, double expense) {
        this.volume = volume;
        this.expense = expense;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }
}
