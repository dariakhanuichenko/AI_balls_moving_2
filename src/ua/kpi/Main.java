package ua.kpi;

public class Main {

    public static void main(String[] args) {

        System.out.println("\n--------------START-----------------------");
        int maxNumberOfSteps = Integer.MAX_VALUE;

        Algorithm algorithm = new Algorithm();
        System.out.println(algorithm.algorithmBigger(algorithm.getJars(),  maxNumberOfSteps, true));
        System.out.println(algorithm.algorithmBigger(algorithm.getJars(),  maxNumberOfSteps, false));
    }
}
