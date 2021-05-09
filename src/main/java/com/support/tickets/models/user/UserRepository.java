package com.support.tickets.models.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    public User getUserById(Integer id);
}
