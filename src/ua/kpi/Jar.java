package ua.kpi;

import ua.kpi.enums.Capasity;

public class Jar {
    private int isFilled;
    private Capasity capasity;

    public Jar() {
    }

    public Jar(int isFilled, Capasity capasity) {
        this.isFilled = isFilled;
        this.capasity = capasity;
    }

    public int getIsFilled() {
        return isFilled;
    }

    public void setIsFilled(int isFilled) {
        this.isFilled = isFilled;
    }

    public Capasity getCapasity() {
        return capasity;
    }

    public void setCapasity(Capasity capasity) {
        this.capasity = capasity;
    }

    public  void makeEmpty() {
        this.isFilled = 0;
    }

    @Override
    public String toString() {
        return "Jar{" +
                "isFilled=" + isFilled +
                ", capasity=" + capasity +
                '}';
    }

    @Override
    protected Object clone() {//throws CloneNotSupportedException {
        Jar clone = null;
//        try {
//            clone = (Jar) super.clone();
            clone = new Jar (this.getIsFilled(), this.getCapasity());
//        } catch (CloneNotSupportedException e) {
//            System.out.println(e);
//        }
        return clone;
    }

}
