package com.bnz.services.quizzes.repository;

import com.bnz.shared.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
