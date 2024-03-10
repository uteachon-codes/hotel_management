package com.hotel.repository;

import com.hotel.model.Reservation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@DataJpaTest
class ReservationRepositoryTest {


    @Autowired
    private ReservationRepository reservationRepository;

    Reservation reservation;
    SimpleDateFormat sdf;

    @BeforeEach
    void setUp() {
    sdf = new SimpleDateFormat("yyyy-MM-dd");

        reservation = new Reservation();
        reservation.setReservationDate(new Date());
        reservation.setId(10);
        reservation.setReservationMode(1);
        reservation.setCustomerId(2);
        reservation.setRoomId(1);
        try {
            reservation.setCheckInDate(sdf.parse("2024-12-05"));
            reservation.setCheckOutDate(sdf.parse("2024-12-15"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        reservation.setPaymentMode(2);
        reservation.setRoomKey("yes");

        reservation.setUpdateDate(new Date());

        reservationRepository.save(reservation);
    }

    @AfterEach
    void tearDown() {

        reservation = null;
        sdf = null;
    }

    @Test
    void testFindByCheckInDateBetween() throws Exception{


        assertThat(reservationRepository.findByCheckInDateBetween(sdf.parse("2024-12-01"),sdf.parse("2025-12-01")).get(0).getCustomerId()).isEqualTo(reservation.getCustomerId());
    }

    @Test
    void testFindByCustomerId() {
        assertThat(reservationRepository.findByCustomerId(2).get(0).getPaymentMode()).isEqualTo(reservation.getPaymentMode());
    }
}