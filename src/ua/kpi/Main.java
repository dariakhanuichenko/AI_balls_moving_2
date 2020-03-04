package ua.kpi;

import java.util.List;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        Field field = new Field();
//        field.getCells().forEach(System.out::print);

        System.out.println("\n--------------START-----------------------");
        field.algorithm(field, 0);
        List<Field> algorithm = field.getResultSuccessStack();
        for (int i = 0; i < algorithm.size(); i++){
            for (Cell cell: algorithm.get(i).getCells()){
                System.out.print(cell.toString());
            }
            System.out.println();
        }
//        System.out.println(resultStack.size());
    };


}
