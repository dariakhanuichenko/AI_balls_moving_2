package ua.kpi;

import java.util.List;

public class ConsoleOutputUtility {
    public static void printResult(List<List<Jar>> jars){
        jars.forEach( j -> print(j));
    }

    public static void print(List<Jar> jars) {
        for (Jar jar : jars) {
            System.out.println(jar);
        }
        System.out.println("-----------------------------");
    }

}
