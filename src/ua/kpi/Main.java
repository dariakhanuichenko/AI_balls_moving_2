package ua.kpi;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("\n--------------START-----------------------");
        int maxNumberOfSteps = Integer.MAX_VALUE;

        Algorithm algorithm = new Algorithm();
        List<List<Jar>> result = new LinkedList<>();
       algorithm.start(algorithm, result,maxNumberOfSteps);

    }
}
