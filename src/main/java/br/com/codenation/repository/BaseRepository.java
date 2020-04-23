package br.com.codenation.repository;

import java.util.List;

public interface BaseRepository<T> {
    void save(T obj);
    boolean existsById(Long id);
    String findById(Long id);
    List<Long> findAll();
}
