package com.suhan.cargo.domain;

/**
 * Класс - обертка, единица работы.
 */
public final class UnitOfWork<T> {

    private final boolean dirty;
    private final T object;

    public UnitOfWork(T object, boolean dirty) {
        this.dirty = dirty;
        this.object = object;
    }

    public boolean isDirty() {
        return dirty;
    }

    public T object() {
        return object;
    }

}
