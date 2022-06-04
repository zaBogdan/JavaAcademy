package com.bnz.services.users.repository;

import com.bnz.shared.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User getUserById(String id);

}
