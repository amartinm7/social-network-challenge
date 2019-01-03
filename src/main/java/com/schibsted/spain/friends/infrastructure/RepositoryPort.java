package com.schibsted.spain.friends.infrastructure;

import java.util.Optional;

public interface RepositoryPort<T,K> {
    boolean save(T entity);
    boolean remove(K key);
    Optional<T> find(K key);
    boolean exists(K key);
}
