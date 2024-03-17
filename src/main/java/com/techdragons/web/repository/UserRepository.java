package com.techdragons.web.repository;

import com.techdragons.web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);

    Collection<? extends User> findByFirstNameContains(String string);

    Collection<? extends User> findByLastNameContains(String string);
}
