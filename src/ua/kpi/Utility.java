package ua.kpi;

import ua.kpi.enums.Actions;
import ua.kpi.enums.Color;

public class Utility {
    public static void canMove(Field field) {
        for (int i = 0; i < field.getCells().size(); i++) {
            Ball ball = field.getCells().get(i).getBall();

            if (ball != null) {
                if (ball.getColor().equals(Color.WHITE)) {
                        // move to 1 cell left
                    if (i - 1 >= 0 && field.getCells().get(i - 1).isEmpty()) {
                        ball.setAction(Actions.WHITE_LEFT_1);
                        // move to 2 cell left
                    } else if (i - 2 >= 0 && field.getCells().get(i - 2).isEmpty()) {
                        ball.setAction(Actions.WHITE_LEFT_2);
                    } else ball.setAction(Actions.NOTHING);
                }
                if (ball.getColor().equals(Color.BLACK)) {
                        // move to 1 cell right
                    if (i + 1 < field.getCells().size() && field.getCells().get(i + 1).isEmpty()) {
                        ball.setAction(Actions.BLACK_RIGHT_1);
                        // move to 2 cell right
                    } else if (i + 2 < field.getCells().size() && field.getCells().get(i + 2).isEmpty()) {
                        ball.setAction(Actions.BLACK_RIGHT_2);
                    } else ball.setAction(Actions.NOTHING);
                }
            }
        }
    }
}
