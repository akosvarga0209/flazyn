package com.flazyn.repository;

import com.flazyn.entities.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement,Long> {
    Optional<Advertisement> findById(Long id);
}
