package com.bnz.services.auth.respository;

import com.bnz.shared.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findByUsername(String userName);
    User findByEmail(String email);
}
