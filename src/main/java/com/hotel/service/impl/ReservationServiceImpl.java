package com.hotel.service.impl;

import com.hotel.model.Reservation;
import com.hotel.repository.ReservationRepository;
import com.hotel.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {


    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Reservation createReservation(Reservation reservation) {

        Date currentDate = new Date();
        reservation.setReservationDate(currentDate);
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getReservationBetweenCheckInDates(Date startDate, Date endDate) {
        return reservationRepository.findByCheckInDateBetween(startDate,endDate);
    }
}
