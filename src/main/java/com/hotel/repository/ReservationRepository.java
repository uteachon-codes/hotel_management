package com.hotel.repository;

import com.hotel.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // This method returns the reservation(s) between a particula checkin dates
    List<Reservation> findByCheckInDateBetween(Date startDate, Date endDate);

    // This method returns the reservation(s) given a customer id
    List<Reservation> findByCustomerId(int customerId);
}

