package com.ss.restaapispring.repository;

import com.ss.restaapispring.entity.VotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotesRepository extends JpaRepository<VotesEntity, Long> {

}
