package com.hotel.service.impl;

import com.hotel.model.Customer;
import com.hotel.model.Reservation;
import com.hotel.repository.ReservationRepository;
import com.hotel.service.CustomerService;
import com.hotel.service.ReservationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private CustomerService customerService;

    private Customer customer;

    @InjectMocks
    private ReservationService reservationService = new ReservationServiceImpl(reservationRepository,customerService);

    AutoCloseable autoCloseable;

    private Reservation reservation;

    SimpleDateFormat sdf;
    @BeforeEach
    void setUp() {

        sdf = new SimpleDateFormat("yyyy-MM-dd");

        reservation = new Reservation();
        reservation.setReservationDate(new Date());
        reservation.setId(10);
        reservation.setReservationMode(1);
        reservation.setCustomerId(20);
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



        customer = new Customer();
        customer.setFirstName("Robert");
        customer.setLastName("Williams");
        customer.setId(20);
        customer.setCity("Denver");
        customer.setZip("72204");
        customer.setEmailAddress("basith@gmail.com");
        customer.setPhoneNumber("5015214252");
        customer.setStreetAddress("1701 W Park");
        customer.setState("AR");
        customer.setCountry("USA");
        customer.setAdditionalAddressInfo("XYZ");
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {

        autoCloseable.close();
    }

    @Test
    void testCreateReservation()  {

        mock(Reservation.class);
        mock(ReservationRepository.class);
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        assertThat(reservationService.createReservation(reservation).getCustomerId()).isEqualTo(reservation.getCustomerId());
        assertThat(reservationService.createReservation(reservation).getReservationDate()).isEqualTo(reservation.getReservationDate());
    }

    @Test
    void testGetAllReservations() {
        mock(Reservation.class);
        mock(ReservationRepository.class);

        when(reservationRepository.findAll()).thenReturn(new ArrayList<>(Collections.singleton(reservation)));

        assertThat(reservationService.getAllReservations().get(0).getPaymentMode()).isEqualTo(reservation.getPaymentMode());

    }

    @Test
    void testGetReservationBetweenCheckInDates() throws ParseException {
        mock(Reservation.class);
        mock(ReservationRepository.class);

        when(reservationRepository.findByCheckInDateBetween(sdf.parse("2024-10-12"),sdf.parse("2024-12-31"))).thenReturn(new ArrayList<>(Collections.singleton(reservation)));

        assertThat(reservationService.getReservationBetweenCheckInDates(sdf.parse("2024-10-12"),sdf.parse("2024-12-31")).get(0).getCustomerId()).isEqualTo(reservation.getCustomerId());
    }

    @Test
    void testGetReservationCustomerPartialFirstName() {

        mock(Reservation.class);
        mock(ReservationRepository.class);

        when(reservationRepository.findByCustomerId(20)).thenReturn(new ArrayList<>(Collections.singleton(reservation)));
        when(customerService.getCustomerByPartialFirstName("Robe")).thenReturn(new ArrayList<>(Collections.singleton(customer)));

        assertThat(reservationService.getReservationCustomerPartialFirstName("Robe").get(0).get(0).getCustomerId()).isEqualTo(reservation.getCustomerId());

    }

    @Test
    void testReservationsByCustomerId() {

        mock(Reservation.class);
        mock(ReservationRepository.class);

        when(reservationRepository.findByCustomerId(20)).thenReturn(new ArrayList<>(Collections.singleton(reservation)));

        assertThat(reservationService.reservationsByCustomerId(20).get(0).getPaymentMode()).isEqualTo(reservation.getPaymentMode());

    }

}