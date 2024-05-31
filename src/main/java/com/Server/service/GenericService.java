package com.Server.service;

import java.util.List;

public interface GenericService<T, ID> {
    void add(T entity);
    T get(ID id);
    List<T> getAll();
    boolean delete(ID id);
    boolean update(ID id, T entity);
}
