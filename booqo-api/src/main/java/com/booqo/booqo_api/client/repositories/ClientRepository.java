package com.booqo.booqo_api.client.repositories;

import com.booqo.booqo_api.client.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByCenterId(Long centerId);
}