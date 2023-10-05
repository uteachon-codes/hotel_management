package com.hotel.controller;

import com.hotel.model.Reservation;
import com.hotel.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/reserve")
public class ReservationInfoController {

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation){

        return new ResponseEntity<Reservation>(reservationService.createReservation(reservation),HttpStatus.OK);

    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public ResponseEntity<List<Reservation>> getAllReservations(){

        return new ResponseEntity<List<Reservation>>(reservationService.getAllReservations(),HttpStatus.OK);

    }



    @RequestMapping(path = "/get/checkin/between-dates", method = RequestMethod.GET)
    public ResponseEntity<List<Reservation>> getReservationBetweenCheckInDates(@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate) throws ParseException {
//        Sample request
//        http://localhost:8080/reserve/get/checkin/between-dates?startDate=2024-01-01&endDate=2024-12-31

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date sDate = dateFormat.parse(startDate);
        Date eDate = dateFormat.parse(endDate);
        return new ResponseEntity<List<Reservation>>(reservationService.getReservationBetweenCheckInDates(sDate,eDate),HttpStatus.OK);

    }
}