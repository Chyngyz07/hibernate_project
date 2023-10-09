package org.peaksoft.service;

import org.peaksoft.model.entities.Student;

import java.util.List;

public interface Service<T> {
    void create(T t);

    void update(Long id,T t);

    List<T> getAll();

    T getById(Long id);

    String deleteById(Long id);
}
