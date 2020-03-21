package ua.kpi;

import ua.kpi.enums.Capasity;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {

    private List<Jar> jars;

    public Algorithm() {
        this.jars = new ArrayList<>();
        jars.add(new Jar(0, Capasity.THREE_LITER));
        jars.add(new Jar(0, Capasity.FIVE_LITER));
    }

    public List<Jar> getJars() {
        return jars;
    }

    private boolean success(List<Jar> clone) {
        return clone.get(0).getIsFilled() == 4 || clone.get(1).getIsFilled() == 4;
    }

    private int fillUpBigger(List<Jar> clone, int counter) {
        if (clone.get(0).getCapasity().get() > clone.get(1).getCapasity().get()) {
            clone.get(0).getCapasity().fillUp(clone.get(0));
            counter++;
            print(clone);
        } else {
            clone.get(1).getCapasity().fillUp(clone.get(1));
            counter++;
            print(clone);
        }
        return counter;
    }

    private int fillUpSmaller(List<Jar> clone, int counter) {
        if (clone.get(0).getCapasity().get() < clone.get(1).getCapasity().get()) {
            clone.get(0).getCapasity().fillUp(clone.get(0));
            counter++;
            print(clone);
        } else {
            clone.get(1).getCapasity().fillUp(clone.get(1));
            counter++;
            print(clone);
        }
        return counter;
    }

    private int makeEmptyAndMove(List<Jar> clone, int counter, int i, boolean flag) {
        clone.get(i).makeEmpty();
        counter++;

        move(clone, flag);
        counter++;
        print(clone);
        return counter;
    }

    public int algorithmBigger(List<Jar> jars, int maxNumberOfSteps, boolean flag) {
        List<Jar> clone = cloneJarList(jars);
        int counter = 0;

        while (!success(clone)) {
            print(clone);
            if (counter >= maxNumberOfSteps) return counter;

            // наполнить больший сосуд водой из-под крана
            if (flag) {
                counter = fillUpBigger(clone, counter);
            } else {
                counter = fillUpSmaller(clone, counter);
            }
            do {
                //перелить с большей в меншую
                if (clone.get(0).getCapasity().isFull(clone.get(0)) || clone.get(1).getCapasity().isFull(clone.get(1))) {
                    move(clone, flag);
                    counter++;
                    print(clone);

                    if (success(clone)) return counter;
                }


                if( flag) { // наполнен ли меньший сосуд
                    if (clone.get(0).getCapasity().get() < clone.get(1).getCapasity().get()) {
                        if (clone.get(0).getCapasity().isFull(clone.get(0))) {
                            //— опорожнить меньший сосуд, вылив воду в раковину;

                            counter = makeEmptyAndMove(clone, counter, 0, flag);
                            break;
                        } else break;
                    } else {
                        if (clone.get(1).getCapasity().isFull(clone.get(1))) {
                            //— опорожнить меньший сосуд, вылив воду в раковину;
                            counter = makeEmptyAndMove(clone, counter, 1, flag);

                            break;
                        } else break;
                    }
                } else { // наполнен ли больший сосуд
                    if (clone.get(0).getCapasity().get() > clone.get(1).getCapasity().get()) {
                        if (clone.get(0).getCapasity().isFull(clone.get(0))) {
                            //— опорожнить больший сосуд, вылив воду в раковину;

                            counter = makeEmptyAndMove(clone, counter, 0, flag);
                            break;
                        } else break;
                    } else {
                        if (clone.get(1).getCapasity().isFull(clone.get(1))) {
                            //— опорожнить больший сосуд, вылив воду в раковину;
                            counter = makeEmptyAndMove(clone, counter, 1, flag);

                            break;
                        } else break;
                    }
                }

            } while (!success(clone));
        }
        return counter;
    }

    public void move(List<Jar> jars, boolean flag) {
        if (jars.size() == 2) {
            if (jars.get(0).getCapasity().get() < jars.get(1).getCapasity().get() && jars.get(0).getIsFilled() != 0) {
                moveFromSmaller(jars.get(0), jars.get(1));
            }
            if (jars.get(1).getCapasity().get() < jars.get(0).getCapasity().get() && jars.get(1).getIsFilled() != 0) {
                moveFromSmaller(jars.get(1), jars.get(0));
            }
            if (jars.get(0).getCapasity().get() > jars.get(1).getCapasity().get() && jars.get(1).getIsFilled() != 0) {
                moveFromBigger(jars.get(1), jars.get(0), flag);
            }
            if (jars.get(1).getCapasity().get() > jars.get(0).getCapasity().get() ) {
                moveFromBigger(jars.get(0), jars.get(1), flag);
            }

        }
    }

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

    public void moveFromBigger(Jar smaller, Jar bigger, boolean flag) {
//        if(  smaller.getIsFilled() == 0){
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
