package org.peaksoft.service;

import java.util.List;

public interface Service<T> {
    void create(T t);

    void update(T t);

    List<T> getAll();

    T getById(Long id);

    String deleteById(Long id);
}
