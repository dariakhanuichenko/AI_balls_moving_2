package ua.kpi;

import ua.kpi.enums.Capaсity;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {

    private List<Jar> jars;

    public Algorithm() {
        this.jars = new ArrayList<>();
        jars.add(new Jar(0, Capaсity.THREE_LITER));
        jars.add(new Jar(0, Capaсity.FIVE_LITER));
    }

    public List<Jar> getJars() {
        return jars;
    }

    private boolean success(List<Jar> clone) {
        return clone.get(0).getIsFilled() == 4 || clone.get(1).getIsFilled() == 4;
    }

    // запуск алгоритма
    public void start(Algorithm algorithm, List<List<Jar>> result, int maxNumberOfSteps) {
        result = algorithm.algorithm(algorithm.getJars(), maxNumberOfSteps, result, false);
//        ConsoleOutputUtility.printResult(result);
        if (maxNumberOfSteps > result.size() - 1)
            maxNumberOfSteps = result.size() - 1;
//        System.out.println(maxNumberOfSteps);
        result = algorithm.algorithm(algorithm.getJars(), maxNumberOfSteps, result, true);
        if (maxNumberOfSteps > result.size() - 1)
            maxNumberOfSteps = result.size() - 1;
        ConsoleOutputUtility.printResult(result);
        System.out.println(result.size() - 1);
    }

    // заполнить 5ти литровое ведро
    private int fillUpBigger(List<Jar> clone, List<List<Jar>> result, int counter) {
        if (clone.get(0).getCapaсity().get() > clone.get(1).getCapaсity().get()) {
            clone.get(0).getCapaсity().fillUp(clone.get(0));
            counter++;
            /*print(clone);*/
            result.add(cloneJarList(clone));
        } else {
            clone.get(1).getCapaсity().fillUp(clone.get(1));
            counter++;
            /*print(clone);*/
            result.add(cloneJarList(clone));
        }
        return counter;
    }

    // заполнить 3х литровое ведро
    private int fillUpSmaller(List<Jar> clone, List<List<Jar>> result, int counter) {
        if (clone.get(0).getCapaсity().get() < clone.get(1).getCapaсity().get()) {
            clone.get(0).getCapaсity().fillUp(clone.get(0));
            counter++;
            /*print(clone);*/
            result.add(cloneJarList(clone));
        } else {
            clone.get(1).getCapaсity().fillUp(clone.get(1));
            counter++;
            /*print(clone);*/
            result.add(cloneJarList(clone));
        }
        return counter;
    }

    private int makeEmptyAndMove(List<Jar> clone, List<List<Jar>> result, int counter, int i, boolean flag) {
        clone.get(i).makeEmpty();
        counter++;

        move(clone, flag);
        counter++;
        /*print(clone);*/
        result.add(cloneJarList(clone));
        return counter;
    }

    public List<List<Jar>> algorithm(List<Jar> clone, int maxNumberOfSteps, List<List<Jar>> result, boolean flag) {
//        List<List<Jar>> result = new LinkedList<>();
        int counter = 0;
        while (!success(clone)) {
            /*print(clone);*/
//            result.add(cloneJarList(clone));

            if (counter >= maxNumberOfSteps) throw new RuntimeException();

            // наполнить больший/меньший сосуд водой из-под крана
            if (flag) {
                counter = fillUpBigger(clone, result, counter);
            } else {
                counter = fillUpSmaller(clone, result, counter);
            }
            do {
                //перелить с большей в меншую
                if (clone.get(0).getCapaсity().isFull(clone.get(0)) || clone.get(1).getCapaсity().isFull(clone.get(1))) {
                    move(clone, flag);
                    counter++;
                    /*print(clone);*/
                    result.add(cloneJarList(clone));

                    if (success(clone)) return result;
                }


                if (flag) { // наполнен ли меньший сосуд
                    if (clone.get(0).getCapaсity().get() < clone.get(1).getCapaсity().get()) {
                        if (clone.get(0).getCapaсity().isFull(clone.get(0))) {

                            //— опорожнить меньший сосуд, вылив воду в раковину;
                            counter = makeEmptyAndMove(clone, result, counter, 0, flag);
                            break;
                        } else break;
                    } else {
                        if (clone.get(1).getCapaсity().isFull(clone.get(1))) {
                            //— опорожнить меньший сосуд, вылив воду в раковину;
                            counter = makeEmptyAndMove(clone, result, counter, 1, flag);

                            break;
                        } else break;
                    }
                } else { // наполнен ли больший сосуд
                    if (clone.get(0).getCapaсity().get() > clone.get(1).getCapaсity().get()) {
                        if (clone.get(0).getCapaсity().isFull(clone.get(0))) {
                            //— опорожнить больший сосуд, вылив воду в раковину;

                            counter = makeEmptyAndMove(clone, result, counter, 0, flag);
                            break;
                        } else break;
                    } else {
                        if (clone.get(1).getCapaсity().isFull(clone.get(1))) {
                            //— опорожнить больший сосуд, вылив воду в раковину;
                            counter = makeEmptyAndMove(clone, result, counter, 1, flag);

                            break;
                        } else break;
                    }
                }

            } while (!success(clone));
        }
        return result;
    }

    // перелить воду с одного ведра в другое
    public void move(List<Jar> jars, boolean flag) {
        if (jars.size() == 2) {
            if (jars.get(0).getCapaсity().get() < jars.get(1).getCapaсity().get() && jars.get(0).getIsFilled() != 0) {
                moveFromSmaller(jars.get(0), jars.get(1));
            }
            if (jars.get(1).getCapaсity().get() < jars.get(0).getCapaсity().get() && jars.get(1).getIsFilled() != 0) {
                moveFromSmaller(jars.get(1), jars.get(0));
            }
            if (jars.get(0).getCapaсity().get() > jars.get(1).getCapaсity().get() && jars.get(1).getIsFilled() != 0) {
                moveFromBigger(jars.get(1), jars.get(0), flag);
            }
            if (jars.get(1).getCapaсity().get() > jars.get(0).getCapaсity().get()) {
                moveFromBigger(jars.get(0), jars.get(1), flag);
            }

        }
    }

    // перелить с 3-литрового ведра в 5-ти литровое
    public void moveFromSmaller(Jar smaller, Jar bigger) {
        int additional = 5 - bigger.getIsFilled();

        if (additional > smaller.getIsFilled()) {
            bigger.setIsFilled(bigger.getIsFilled() + smaller.getIsFilled());
            smaller.makeEmpty();
        } else {
            bigger.setIsFilled(bigger.getIsFilled() + additional);
            smaller.setIsFilled(smaller.getIsFilled() - additional);
        }
    }

    // перелить с 5-литрового ведра в 3-х литровое
    public void moveFromBigger(Jar smaller, Jar bigger, boolean flag) {
        if (flag) {
            int additional = 3 - smaller.getIsFilled();

            if (additional > bigger.getIsFilled()) {
                smaller.setIsFilled(smaller.getIsFilled() + bigger.getIsFilled());
                bigger.makeEmpty();
            } else {
                smaller.setIsFilled(smaller.getIsFilled() + additional);
                bigger.setIsFilled(bigger.getIsFilled() - additional);
            }
        }
    }

    public void print(List<Jar> jars) {
        for (Jar jar : jars) {
            System.out.println(jar);
        }
        System.out.println("-----------------------------");
    }

    private List<Jar> cloneJarList(List<Jar> jars) {
        List<Jar> clone = new ArrayList<>();
        for (Jar jar : jars) {
            clone.add((Jar) jar.clone());
        }
        return clone;
    }
}
