package ua.kpi;


import java.util.ArrayList;
import java.util.List;

public class Field implements Cloneable {

    private List<Cell> cells;

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public Field() {
        cells = new ArrayList<>(8);
        for (int i = 0; i < 8; i++) {
            if (i < 4) {
                cells.add(new Cell(new Ball(Color.BLACK, false, Actions.NOTHING), i));
            } else if (i > 4) {
                cells.add(new Cell(new Ball(Color.WHITE, false, Actions.NOTHING), i));

            } else if (i == 4) {
                cells.add(new Cell(null, i));
            }
        }
    }



    public void getNextMove(Integer counter, Field original) {
        Field currentField = original.clone();
        Field field1,field2,field3,field4;
        System.out.println(counter);

        for (Cell cell : currentField.cells) {

            if (cell.getBall() != null && cell.getBall().getAction() == Actions.BLACK_RIGHT_2) {
                if (cell.getIndex() + 2 < currentField.cells.size()) {
                    System.out.println("BLACK RIGHT 2=" + cell.getIndex());

                    swapBalls(currentField.getCells(), cell.getIndex(), cell.getIndex() + 2);
                    Utility.canMove(currentField.cells);
                    field1= currentField.clone();

                    System.out.println(" ---- black right 2 -------");

                    currentField.getCells().forEach(System.out::println);

                    if (counter != 0 && deadEnd(field1.getCells(), field1.getIndexOfEmptyCell(field1.getCells()), counter)) {
                        return;
                    } else {
                        counter++;
                        getNextMove(counter, field1);
                    }
                }
            }
            if (cell.getBall() != null && cell.getBall().getAction() == Actions.BLACK_RIGHT_1) {
                if (cell.getIndex() + 1 < currentField.cells.size()) {
                    System.out.println("BLACK RIGHT 1=" + cell.getIndex());
                    swapBalls(currentField.getCells(), cell.getIndex(), cell.getIndex() + 1);

                    Utility.canMove(currentField.cells);// проверка можна ли переместить куда то
                    field2= currentField.clone();

                    System.out.println(" ---- black right 1 -------");

                    currentField.getCells().forEach(System.out::println);
                    if ( deadEnd(field2.getCells(), field2.getIndexOfEmptyCell(field2.getCells()), counter)) {
                        break;
                    } else {
                        counter++;
                        // рекурсивный вызов перемещения
                        getNextMove(counter, field2);
                    }
                }
            }
            if (cell.getBall() != null && cell.getBall().getAction() == Actions.WHITE_LEFT_1) {
                if (counter != 0 && cell.getIndex() - 1 >= 0) {
                    System.out.println("WHITE LEFT 1=" + cell.getIndex());

                    swapBalls(currentField.getCells(), cell.getIndex(), cell.getIndex() - 1);
                    Utility.canMove(currentField.cells);
                    field3= currentField.clone();

                    System.out.println(" ---- white left 1 -------");

                    currentField.getCells().forEach(System.out::println);

                    if ( deadEnd(field3.getCells(), field3.getIndexOfEmptyCell(field3.getCells()), counter)) {
                        return;
                    } else {
                        counter++;
                        getNextMove(counter, field3);
                    }
                }
            }
            if (cell.getBall() != null && cell.getBall().getAction() == Actions.WHITE_LEFT_2) {
                if (cell.getIndex() - 2 >= 0) {
                    System.out.println("WHITE LEFT 2=" + cell.getIndex());

                    System.out.println(" ---- white left 2 -------");
                     swapBalls(currentField.getCells(), cell.getIndex(), cell.getIndex() - 2);
                     Utility.canMove(currentField.cells);
                    field4= currentField.clone();

                    currentField.getCells().forEach(System.out::println);

                    if (deadEnd(field4.getCells(), field4.getIndexOfEmptyCell(field4.getCells()), counter)) {
                        break;
                    } else {
                        counter++;
                        getNextMove(counter,field4);
                    }
                }
            }
        }
    }

    @Override
    protected Field clone() {
        Field clone = null;
        try {
            clone = (Field) super.clone();
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

        Ball temp ;
        if (cells.get(i).getBall() == null) {
            if(cells.get(j).getBall() !=null){
            cells.get(i).setBall(new Ball());
            cells.get(i).setBall(cells.get(j).getBall());
            cells.get(i).getBall().setMoved(true);
            cells.get(j).setBall(null);}
        } else if (cells.get(j).getBall() == null) {
            cells.get(j).setBall(new Ball());
            cells.get(j).setBall(cells.get(i).getBall());
            cells.get(j).getBall().setMoved(true);
            cells.get(i).setBall(null);
        } else {
            temp =cells.get(i).getBall();
            cells.get(i).setBall(cells.get(j).getBall());
            cells.get(j).getBall().setMoved(true);
            cells.get(i).getBall().setMoved(true);
            cells.get(j).setBall(temp);
        }
//        cells.forEach(cell -> {
//            if (cell.getBall() != null) cell.getBall().setAction(Actions.NOTHING);
//        });
        return cells;
    }

    private boolean deadEnd(List<Cell> cells, Integer i, Integer counter) {

//        System.out.println("------deadend--------");
//        cells.forEach(System.out::println);
        for (int j = 0; j < cells.size() ; j++) {
            if( j==i ) continue;
            else if( cells.get(j).getBall().getAction() != Actions.NOTHING) return false;
        }
        return true;
//        if( i+2<cells.size())
//            if( cells.get(i+1).getBall().getColor() == cells.get(i + 2).getBall().getColor()
//                    &&
//                    (cells.get(i+1).getBall().getMoved() || cells.get(i + 2).getBall().getMoved())) {
//            System.out.println("DDDDDDEEEAAADDD");
//            counter --;
//            return true;
//
//        }
//            if(i -2 >=0 )
//                if(cells.get(i-1).getBall().getColor() == cells.get(i - 2).getBall().getColor()
//                        &&
//                        (cells.get(i-1).getBall().getMoved() || cells.get(i -2).getBall().getMoved())) {
//                System.out.println("DDDDDDEEEAAADDD");
//                counter --;
//                return true;
//            }

    }

    public Integer getIndexOfEmptyCell(List<Cell> cells){
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).isEmpty()) return i;
        };
        return -1;
    }
}
