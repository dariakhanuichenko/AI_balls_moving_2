package ua.kpi;

public class Cell implements Cloneable {

    private Ball ball;

    public Cell() {
    }

    Cell(Ball ball) {
        this.ball = ball;
    }

    Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }


    boolean isEmpty() {
        return ball == null;
    }


    @Override
    public String toString() {
       String str ="";/*= "Cell{";
        if (ball != null) str += "ball=" + ball.toString();
                str += '}';*/
        if( ball==null)
            str ="_";
            else if( ball.getColor().equals(Color.WHITE))
            str= "\u26AA";
        else if (ball.getColor().equals(Color.BLACK))
            str="\u26AB";

        return str;
    }

    @Override
    protected Cell clone() {
        Cell clone = null;
        try {
            clone = (Cell) super.clone();
            if (this.getBall() == null) {
                clone = new Cell(null);
            } else {
                clone = new Cell(this.getBall().clone());
            }
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }
        return clone;
    }
}
