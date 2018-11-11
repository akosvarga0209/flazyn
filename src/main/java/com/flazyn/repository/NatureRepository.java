package com.flazyn.repository;

import com.flazyn.entities.Nature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NatureRepository extends JpaRepository<Nature,Long> {
    List<Nature> findAll();
}
