package ua.kpi;


import ua.kpi.enums.Actions;
import ua.kpi.enums.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Field implements Cloneable {

    private List<Cell> cells;

    private String action;

    private List<Field> successStack = new ArrayList<>();

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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public List<Field> getResultSuccessStack() {
        successStack.add(this);
        Collections.reverse(successStack);
        return successStack;
    }

    @Override
    protected Field clone() {
        Field clone = null;
        try {
            clone = (Field) super.clone();
            clone.successStack = new ArrayList<>();
            clone.action = "";
            clone.setCells(new ArrayList<>(8));
            for (Cell cell : cells) {
                clone.getCells().add(cell.clone());
            }
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }
        return clone;
    }

    private List<Cell> swapBalls(List<Cell> cells, Integer i, Integer j) {

        Cell tmp = cells.get(i);
        cells.set(i, cells.get(j));
        cells.set(j, tmp);
        return cells;
    }


    public boolean algorithm(Field field, int counter, int maxNumber) {
        counter++;
        if( counter <=maxNumber){
        // check actions for ball in cell current field
        Utility.canMove(field);
        for (int i = 0; i < field.getCells().size(); i++) {
            if (field.getCells().get(i).getBall() != null && field.getCells().get(i).getBall().getAction() != Actions.NOTHING) {

                // clone current field
                Field clone = field.clone();
                clone.setAction(field.getCells().get(i).getBall().getAction().getStringWithIndex(i));
                // swap empty cell and cell with a ball
                swapBalls(clone.getCells(), i, clone.getCells().get(i).getBall().getAction().returnTargetCell(i));

                // if success -> add current field to stack
                if (success(clone)) {
                    this.successStack.add(clone);
                    return true;
                }

                // if current stack is not empty -> add current field to stack
                if (algorithm(clone, counter, maxNumber)) {
                    this.successStack.add(clone);
                    return true;
                }

            }
        }}

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
