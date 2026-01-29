package com.booqo.booqo_api.booking.repositories;


import com.booqo.booqo_api.booking.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCenterId(Long centerId);
}