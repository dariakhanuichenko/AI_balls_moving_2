package ua.kpi;

public class Utility {
    public static void canMove(Field field) {
        for (int i = 0; i < field.getCells().size(); i++) {

            if (field.getCells().get(i).getBall() != null) {
                if (field.getCells().get(i).getBall().getColor().equals(Color.WHITE)) {
                    if (i - 1 >= 0 && field.getCells().get(i - 1).isEmpty()) {
                        field.getCells().get(i).getBall().setAction(Actions.WHITE_LEFT_1);
                    } else if (i - 2 >= 0 && field.getCells().get(i - 2).isEmpty()) {
                        field.getCells().get(i).getBall().setAction(Actions.WHITE_LEFT_2);
                    } else field.getCells().get(i).getBall().setAction(Actions.NOTHING);
                }
                if (field.getCells().get(i).getBall().getColor().equals(Color.BLACK)) {
                    if (i + 1 < field.getCells().size() && field.getCells().get(i + 1).isEmpty()) {
                        field.getCells().get(i).getBall().setAction(Actions.BLACK_RIGHT_1);
                    } else if (i + 2 < field.getCells().size() && field.getCells().get(i + 2).isEmpty()) {
                        field.getCells().get(i).getBall().setAction(Actions.BLACK_RIGHT_2);
                    } else field.getCells().get(i).getBall().setAction(Actions.NOTHING);
                }
            }
        }
    }
}
