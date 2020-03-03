package ua.kpi;

public class Cell implements Cloneable {

    private Ball ball;
    private Integer index;

    public Cell() {
    }

    public Cell(Ball ball, Integer index) {
        this.ball = ball;
        this.index = index;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public boolean isEmpty() {
        return ball == null;
    }


    @Override
    public String toString() {
        String str = "Cell{";
        if (ball != null)
            str += "ball=" + ball.toString();
        str += ", index=" + index +
                '}';
        return str;
    }

    @Override
    protected Cell clone() {
        Cell clone = null;
        try {
            clone = (Cell) super.clone();
            if (this.getBall() == null) {
                clone = new Cell(null, this.getIndex());
            } else {
                clone = new Cell(this.getBall().clone(), this.getIndex());
            }
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }
        return clone;
    }
}
