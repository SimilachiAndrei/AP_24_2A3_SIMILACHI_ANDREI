package com.Server.controller;

import java.util.List;

public interface GenericController<T, ID> {
    void add(T entity);
    T get(ID id);
    List<T> getAll();
    boolean delete(ID id);
    boolean update(ID id, T entity);
}
