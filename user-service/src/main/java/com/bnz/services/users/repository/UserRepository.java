package com.bnz.services.users.repository;

import com.bnz.shared.models.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    @Query(fields = "{'password': 0}")
    User getUserById(String id);
}
