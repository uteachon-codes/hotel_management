package com.hotel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hotel.model.Reservation;
import com.hotel.service.ReservationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservationInfoController.class)
public class ReservationInfoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    Reservation reservationOne;

    Reservation reservationTwo;

    List<Reservation> reservationList = new ArrayList<>();

    SimpleDateFormat sdf;
    @BeforeEach
    void setUp() {
        sdf = new SimpleDateFormat("yyyy-MM-dd");

        reservationOne = new Reservation();
        reservationOne.setId(10);
        reservationOne.setReservationMode(1);
        reservationOne.setCustomerId(20);
        reservationOne.setRoomId(1);
        try {
            reservationOne.setReservationDate(sdf.parse("2024-12-05"));
            reservationOne.setCheckInDate(sdf.parse("2024-12-05"));
            reservationOne.setCheckOutDate(sdf.parse("2024-12-15"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        reservationOne.setPaymentMode(2);
        reservationOne.setRoomKey("yes");

        reservationOne.setUpdateDate(new Date());

        reservationTwo = new Reservation();
        reservationTwo.setReservationDate(new Date());
        reservationTwo.setId(15);
        reservationTwo.setReservationMode(3);
        reservationTwo.setCustomerId(1);
        reservationTwo.setRoomId(100);
        try {
            reservationTwo.setCheckInDate(sdf.parse("2025-12-05"));
            reservationTwo.setCheckOutDate(sdf.parse("2025-12-15"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        reservationTwo.setPaymentMode(6);
        reservationTwo.setRoomKey("no");

        reservationTwo.setUpdateDate(new Date());

        reservationList.add(reservationOne);
        reservationList.add(reservationTwo);
    }

    @AfterEach
    void tearDown() {
        reservationList.clear();
        reservationOne = null;
        reservationTwo = null;
    }


    @org.junit.Test(expected = NullPointerException.class)
    public void testCreateReservation() throws Exception{

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);

        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String jsonData =  ow.writeValueAsString(reservationOne);

        when(reservationService.createReservation(reservationOne)).thenReturn(reservationOne);

        this.mockMvc.perform(post("/reserve/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void testGetAllReservations() throws Exception {
        when(reservationService.getAllReservations()).thenReturn(reservationList);

        this.mockMvc.perform(get("/reserve/get"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void testGetReservationBetweenCheckInDates() throws Exception {
        when(reservationService.getReservationBetweenCheckInDates(sdf.parse("2024-01-01"),sdf.parse("2024-12-31")))
                .thenReturn(reservationList);
        this.mockMvc.perform(get("/reserve/get/checkin/between-dates?startDate=2024-01-01&endDate=2024-12-31"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void testGetReservationWithPartialName() throws Exception {

            when(reservationService.getReservationCustomerPartialFirstName("Robe")).thenReturn(new ArrayList<>(Collections.singleton(reservationList)));

            this.mockMvc.perform(get("/reserve/getPartialName/Robe"))
                    .andDo(print())
                    .andExpect(status().isOk());
    }

   @Test
   @WithMockUser
    void testGetreservationsByCustomerId() throws Exception{
        when(reservationService.reservationsByCustomerId(20)).thenReturn(new ArrayList<>(Collections.singleton(reservationOne)));

       this.mockMvc.perform(get("/reserve/get/20"))
               .andDo(print())
               .andExpect(status().isOk());

     }

}