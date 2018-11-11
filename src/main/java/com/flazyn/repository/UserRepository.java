package com.flazyn.repository;


import com.flazyn.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
    Optional<User> findByActivation(String code);
    User findByFbId(String fbId);

}
