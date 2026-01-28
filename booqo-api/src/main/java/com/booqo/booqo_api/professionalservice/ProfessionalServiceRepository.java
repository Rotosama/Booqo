package com.booqo.booqo_api.professionalservice;
import com.booqo.booqo_api.professionalservice.entities.ProfessionalService;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProfessionalServiceRepository extends JpaRepository<ProfessionalService, Long> {
    List<ProfessionalService> findByCenterId(Long centerId);
}