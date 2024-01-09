package org.example.model;

public class Pair {

    private Object objectLeft;
    private Object objectRight;

    public Pair (Object objectL, Object objectR){
        this.objectLeft = objectL;
        this.objectRight = objectR;
    }

    public Object getObjectLeft() {
        return objectLeft;
    }

    public void setObjectLeft(Object objectLeft) {
        this.objectLeft = objectLeft;
    }

    public Object getObjectRight() {
        return objectRight;
    }

    public void setObjectRight(Object objectRight) {
        this.objectRight = objectRight;
    }
}
