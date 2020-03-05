package ua.kpi;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Field field = new Field();

        System.out.println("\n--------------START-----------------------");
        field.algorithm(field, 0,20);
        List<Field> algorithm = field.getResultSuccessStack();
        System.out.println("Понадобилось " + (algorithm.size() - 1) + " шагов");
        System.out.println("Пошаговое объяснение для тех, кто не может решить сам:");
        for (int i = 0; i < algorithm.size(); i++) {
            if (i != 0) {
                System.out.println(algorithm.get(i).getAction());
            }
            for (Cell cell : algorithm.get(i).getCells()) {
                System.out.print(cell.toString());
            }
            System.out.println();
        }
    }
}
