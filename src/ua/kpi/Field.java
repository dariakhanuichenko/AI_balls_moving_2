package ua.kpi;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Field implements Cloneable {

    private List<Cell> cells;

    public List<Field> getSuccessStack() {
        return successStack;
    }

    private List<Field> successStack = new ArrayList<>();

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public Field() {
        cells = new ArrayList<>(8);
        cells.add(new Cell(new Ball(Color.BLACK, false, Actions.NOTHING)));
        cells.add(new Cell(new Ball(Color.BLACK, false, Actions.NOTHING)));
        cells.add(new Cell(new Ball(Color.BLACK, false, Actions.NOTHING)));
        cells.add(new Cell(new Ball(Color.BLACK, false, Actions.NOTHING)));
        cells.add(new Cell(null));
        cells.add(new Cell(new Ball(Color.WHITE, false, Actions.NOTHING)));
        cells.add(new Cell(new Ball(Color.WHITE, false, Actions.NOTHING)));
        cells.add(new Cell(new Ball(Color.WHITE, false, Actions.NOTHING)));

    }

    @Override
    protected Field clone() {
        Field clone = null;
        try {
            clone = (Field) super.clone();
            clone.successStack = new ArrayList<>();
            clone.setCells(new ArrayList<>(8));
            for (Cell cell : cells) {
                clone.getCells().add(cell.clone());
            }
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }
        return clone;
    }

    public List<Cell> swapBalls(List<Cell> cells, Integer i, Integer j) {

        Cell tmp = cells.get(i);
        cells.set(i, cells.get(j));
        cells.set(j, tmp);
        return cells;
    }


    public Integer getIndexOfEmptyCell(List<Cell> cells) {
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).isEmpty()) return i;
        }
        ;
        return -1;
    }

    public boolean algorithm(Field field, int counter) {
        counter++;
        System.out.println("\nNEW BRANCH");
        Utility.canMove(field);
        for (int i = 0; i < field.getCells().size(); i++) {
//            System.out.println("///////////////////////////////");
            if (field.getCells().get(i).getBall() != null && field.getCells().get(i).getBall().getAction() != Actions.NOTHING) {
                // output action
//                System.out.println(field.getCells().get(i).getBall().getAction().getStringWithIndex(i));
                // clone current field
                Field clone = field.clone();
//                clone.getCells().forEach(System.out::print);
                // swap empty cell and cell with a ball
                swapBalls(clone.getCells(), i, clone.getCells().get(i).getBall().getAction().returnTargetCell(i));
//                System.out.println("\n---------------------------------");

                if (success(clone)){
//                    System.out.println("\ncounter = " + counter);
//                    System.out.println("SUCCESS");
//                    counter=0;
                    this.successStack.add(clone);
                    return true;
                }

//                algorithm(clone, counter);
                if (algorithm(clone, counter)){
                    this.successStack.add(clone);
                    return true;
                };
                                // recursion
            }}
//        System.out.println("///////////////////////////////");
        return false;
    }

    private boolean success(Field field) {
        return field.getCells().get(0).getBall() != null && field.getCells().get(0).getBall().getColor().equals(Color.WHITE) &&
                field.getCells().get(1).getBall() != null && field.getCells().get(1).getBall().getColor().equals(Color.WHITE) &&
                field.getCells().get(2).getBall() != null && field.getCells().get(2).getBall().getColor().equals(Color.WHITE) &&
                field.getCells().get(3).getBall() == null &&
                field.getCells().get(4).getBall() != null && field.getCells().get(4).getBall().getColor().equals(Color.BLACK) &&
                field.getCells().get(5).getBall() != null && field.getCells().get(5).getBall().getColor().equals(Color.BLACK) &&
                field.getCells().get(6).getBall() != null && field.getCells().get(6).getBall().getColor().equals(Color.BLACK) &&
                field.getCells().get(7).getBall() != null && field.getCells().get(7).getBall().getColor().equals(Color.BLACK);
    }

    public List<Field> getResultSuccessStack() {
        successStack.add(this);
        Collections.reverse(successStack);
        return successStack;
    }
}

    /*
    method(field){
        for(cell){
            if (cell.getball.canmove){
                move;
                cloneField = clone field;
                method(cloneField);
            }
        }
    }*/
