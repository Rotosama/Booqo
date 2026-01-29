package com.booqo.booqo_api.center.repositories;

import com.booqo.booqo_api.center.entities.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {
    Optional<Center> findByCif(String cif);
}