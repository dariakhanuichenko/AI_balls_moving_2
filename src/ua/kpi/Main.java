package ua.kpi;

public class Main {

    public static void main(String[] args) {

//        Field field = new Field();
        System.out.println("\n--------------START-----------------------");
        int maxNumberOfSteps = Integer.MAX_VALUE;

        Algorithm algorithm = new Algorithm();
        System.out.println(algorithm.algorithmBigger(algorithm.getJars(), maxNumberOfSteps));

//        field.algorithm(field, 0,Integer.MAX_VALUE);
//        List<Field> algorithm = field.getResultSuccessStack();
//        System.out.println("Понадобилось " + (algorithm.size() - 1) + " шагов");
//        System.out.println("Пошаговое объяснение для тех, кто не может решить сам:");
//        for (int i = 0; i < algorithm.size(); i++) {
//            if (i != 0) {
//                System.out.println(algorithm.get(i).getAction());
//            }
//            for (Cell cell : algorithm.get(i).getCells()) {
//                System.out.print(cell.toString());
//            }
//            System.out.println();
//        }
    }
}
