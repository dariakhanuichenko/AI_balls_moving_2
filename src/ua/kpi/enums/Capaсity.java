package ua.kpi.enums;

import ua.kpi.Jar;

public enum Capaсity {
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

    // проверка заполнено ли ведро
    public abstract boolean isFull(Jar jar);

    // заполнить ведро поностью
    public abstract void fillUp(Jar jar);

    // получить объем ведра
    public abstract int get();
}
