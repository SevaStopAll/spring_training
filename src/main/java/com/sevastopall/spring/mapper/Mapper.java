package com.sevastopall.spring.mapper;

public interface Mapper<F,T> {
    T map(F object);

    default T map(F fromobject, T toObject) {
        return toObject;
    }
}
