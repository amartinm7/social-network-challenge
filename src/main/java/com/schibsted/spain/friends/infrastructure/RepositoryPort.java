package com.schibsted.spain.friends.infrastructure;

import com.schibsted.spain.friends.domain.User;

public interface RepositoryPort<T,K> {
    boolean save(T entity);
    boolean remove(K key);
    T find(K key);
    boolean exists(K key);
}
