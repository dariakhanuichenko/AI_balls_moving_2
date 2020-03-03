package ua.kpi;

public class Main {

    public static void main(String[] args) {

        Field field = new Field();
        field.getCells().forEach(System.out::print);

        System.out.println("--------------START-----------------------");
        field.algorithm(field);

    }

}
