package com.suhan.cargo.domain;

/**
 * Абстрактный класс, для валидации данных.
 */
public abstract class AssertionConcern {

    protected void assertArgumentNotNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentNotEmpty(String string, String message) {
        if (string == null || string.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentLength(String string, int maximum, String message) {
        int length = string.trim().length();
        if (length > maximum) {
            throw new IllegalArgumentException(message);
        }
    }

}
