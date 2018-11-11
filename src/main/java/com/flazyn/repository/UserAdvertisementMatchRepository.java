package com.flazyn.repository;

import com.flazyn.entities.UserAdvertisementId;
import com.flazyn.entities.UserAdvertisementMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAdvertisementMatchRepository extends JpaRepository<UserAdvertisementMatch,UserAdvertisementId> {
}
