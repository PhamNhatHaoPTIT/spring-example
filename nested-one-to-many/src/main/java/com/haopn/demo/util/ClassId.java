package com.haopn.demo.util;

import java.util.Objects;

public class ClassId<T> {
    private final Class<T> clazz;
    private final int id;

    public ClassId(Class<T> clazz, int id) {
        this.clazz = clazz;
        this.id = id;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassId<?> classId = (ClassId<?>) o;
        return id == classId.id &&
                Objects.equals(clazz, classId.clazz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clazz, id);
    }
}
