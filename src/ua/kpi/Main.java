package ua.kpi;

public class Main {

    public static void main(String[] args) {

        Field field = new Field();
        field.getCells().forEach(System.out::println);

        System.out.println("--------------CLONE-----------------------");
//       Utility.canMove(field.getCells());
//        Field clone = field.clone();
//        clone.getCells().forEach(System.out::println);
//        System.out.println("--------------Swap-----------------------");
//        field.swapBalls(field.getCells(), 4, 7);
//        field.getCells().forEach(System.out::println);

        field.algorithm(field);


    }

}
