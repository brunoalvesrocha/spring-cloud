package com.techprimers.security.securitydbexample.repository;

import com.techprimers.security.securitydbexample.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author brunorocha
 */
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByName(String username);
}
