package ua.kpi;

import ua.kpi.enums.Capaсity;

public class Jar {
    private int isFilled;
    private Capaсity capaсity;

    public Jar() {
    }

    public Jar(int isFilled, Capaсity capaсity) {
        this.isFilled = isFilled;
        this.capaсity = capaсity;
    }

    public int getIsFilled() {
        return isFilled;
    }

    public void setIsFilled(int isFilled) {
        this.isFilled = isFilled;
    }

    public Capaсity getCapaсity() {
        return capaсity;
    }

    public void setCapaсity(Capaсity capaсity) {
        this.capaсity = capaсity;
    }

    public  void makeEmpty() {
        this.isFilled = 0;
    }

    @Override
    public String toString() {
        return "Jar{" +
                "isFilled=" + isFilled +
                ", capasity=" + capaсity +
                '}';
    }

    @Override
    protected Object clone() {//throws CloneNotSupportedException {
        Jar clone = null;
//        try {
//            clone = (Jar) super.clone();
            clone = new Jar (this.getIsFilled(), this.getCapaсity());
//        } catch (CloneNotSupportedException e) {
//            System.out.println(e);
//        }
        return clone;
    }

}
