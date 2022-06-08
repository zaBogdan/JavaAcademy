package com.bnz.services.quizzes.utils;

public enum AttenderStatus {
    STARTED (0x1),
    IN_VERIFICATION_PROCESS(0x2),
    FINISHED (0x3),
    NOT_FINISHED_IN_TIME (0x4);

    private final int value;

    AttenderStatus(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }
}
