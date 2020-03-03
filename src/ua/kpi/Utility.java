package ua.kpi;

import java.util.List;

public class Utility {
    public static List<Cell> canMove(List<Cell> cells) {
        cells.forEach(cell -> {
            if (cell.getBall() != null) {
                if (cell.getBall().getColor().equals(Color.WHITE)) {
                    if (cell.getIndex() - 1 >= 0 && cells.get(cell.getIndex() - 1).isEmpty()) {
                        cell.getBall().setAction(Actions.WHITE_LEFT_1);
                    } else if (cell.getIndex() - 2 >= 0 && cells.get(cell.getIndex() - 2).isEmpty()) {
                        cell.getBall().setAction(Actions.WHITE_LEFT_2);
                    }else cell.getBall().setAction(Actions.NOTHING);
                } else if (cell.getBall().getColor().equals(Color.BLACK)) {
                    if (cell.getIndex() + 1 < cells.size() && cells.get(cell.getIndex() + 1).isEmpty()) {
                        cell.getBall().setAction(Actions.BLACK_RIGHT_1);
                    } else if (cell.getIndex() + 2 < cells.size() && cells.get(cell.getIndex() + 2).isEmpty()) {
                        cell.getBall().setAction(Actions.BLACK_RIGHT_2);
                    }else cell.getBall().setAction(Actions.NOTHING);
                }
            }
        });
        return cells;
    }
}
