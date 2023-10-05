package com.hotel.service;

import com.hotel.model.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationService {

    public Reservation createReservation(Reservation reservation);

    public List<Reservation> getAllReservations();

    public List<Reservation> getReservationBetweenCheckInDates(Date startDate, Date endDate);
}
