package ua.kpi;

import com.sun.org.apache.regexp.internal.RE;

public enum Actions {
    BLACK_RIGHT_1{
        @Override
        public int returnTargetCell(int start) {
            return start + 1;
        }
    },
    BLACK_RIGHT_2 {
        @Override
        public int returnTargetCell(int start) {
            return start + 2;
        }
    },
    WHITE_LEFT_1 {
        @Override
        public int returnTargetCell(int start) {
            return start - 1;
        }
    },
    WHITE_LEFT_2 {
        @Override
        public int returnTargetCell(int start) {
            return start - 2;
        }
    },
    NOTHING {
        @Override
        public int returnTargetCell(int start) {
            throw new RuntimeException("Nope");
        }
    };

    public abstract int returnTargetCell(int start);

    public String getStringWithIndex(int i) {
        i++;
        switch (this){
            case WHITE_LEFT_1:
                return "<- " + i;
            case WHITE_LEFT_2:
                return "<-- " + i;
            case BLACK_RIGHT_1:
                return i + " ->";
            case BLACK_RIGHT_2:
                return i + " -->";
            default: throw new RuntimeException("opoops");
        }
    }
}
