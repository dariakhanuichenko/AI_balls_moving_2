package ua.kpi;

public class Ball implements Cloneable {

    private Color color;
    private boolean moved;
    private Actions action;

    public Ball() {
    }

    public Ball(Color color, boolean moved, Actions action) {
        this.color = color;
        this.moved = moved;
        this.action = action;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean getMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public Actions getAction() {
        return action;
    }

    public void setAction(Actions action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Ball{" +
                "color=" + color +
                ", moved=" + moved +
                ", action=" + action +
                '}';
    }

    @Override
    protected Ball clone() throws CloneNotSupportedException {
        Ball clone = null;
        try {
            clone = (Ball) super.clone();
            clone = new Ball (this.getColor(), this.getMoved(), this.getAction());
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }
        return clone;
    }
}
