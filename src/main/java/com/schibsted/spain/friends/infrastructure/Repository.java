package com.schibsted.spain.friends.infrastructure;

import com.schibsted.spain.friends.domain.User;

public interface Repository<T,K> {
    boolean save(T entity);
    boolean delete(K key);
    T find(K key);
    boolean exists(K key);
}
