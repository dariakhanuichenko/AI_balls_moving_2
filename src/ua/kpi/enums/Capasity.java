package ua.kpi.enums;

import ua.kpi.Jar;

import java.util.List;

public enum Capasity {
    THREE_LITER {
        @Override
        public boolean isFull(Jar jar) {
            return jar.getIsFilled() == 3;
        }

        @Override
        public void fillUp(Jar jar) {
            jar.setIsFilled(3);
        }

        @Override
        public int get() {
            return 3;
        }
    },
    FIVE_LITER {
        @Override
        public boolean isFull(Jar jar) {
            return jar.getIsFilled() == 5;
        }

        @Override
        public void fillUp(Jar jar) {
            jar.setIsFilled(5);
        }

        @Override
        public int get() {
            return 5;
        }
    };

    public abstract boolean isFull(Jar jar);

    public abstract void fillUp(Jar jar);

    public abstract int get();
}
