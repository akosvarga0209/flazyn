package com.flazyn.repository;

import com.flazyn.entities.UserMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMatchRepository extends JpaRepository<UserMatch,Long> {
}
