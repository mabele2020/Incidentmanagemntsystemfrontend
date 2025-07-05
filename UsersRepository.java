// UserRepository.java
package com.example.project.repository;

import com.example.project.model.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    static User save(User user) {
        return user;
    }
}
