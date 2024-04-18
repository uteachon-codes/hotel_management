package com.hotel.controller;

import com.hotel.model.Reservation;
import com.hotel.service.ReservationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@RestController
@RequestMapping(path = "/reserve")
public class ReservationInfoController {

    private static final Logger logger = LogManager.getLogger(ReservationInfoController.class);

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {

        logger.info("Received POST request to create a reservation at path /reserve//create with request body: {}", reservation);
        return new ResponseEntity<Reservation>(reservationService.createReservation(reservation), HttpStatus.OK);

    }

    @GetMapping("/get")
//    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_MANAGER')" )
    @CrossOrigin
    public ResponseEntity<List<Reservation>> getAllReservations() {
        logger.info("Received GET request to fetch all reservations at /reserve/get.");
        return new ResponseEntity<List<Reservation>>(reservationService.getAllReservations(), HttpStatus.OK);

    }


    @GetMapping("/get/checkin/between-dates")
    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_MANAGER')" )
    public ResponseEntity<List<Reservation>> getReservationBetweenCheckInDates(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) throws ParseException {
//        Sample request
//        http://localhost:8080/reserve/get/checkin/between-dates?startDate=2024-01-01&endDate=2024-12-31

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date sDate = dateFormat.parse(startDate);
        Date eDate = dateFormat.parse(endDate);
        return new ResponseEntity<List<Reservation>>(reservationService.getReservationBetweenCheckInDates(sDate, eDate), HttpStatus.OK);

    }


    /*
     *  This controller method returns the reservations taking input as customer partialName
     * */
    @GetMapping("/getPartialName/{partialName}")
    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_MANAGER')" )
    public ResponseEntity<List<List<Reservation>>> getReservationWithPartialName(@PathVariable String partialName) {
        // Retrieve reservations based on a partial name from the service.
        List<List<Reservation>> reservations = reservationService.getReservationCustomerPartialFirstName(partialName);
        // Return the reservations in a ResponseEntity with an HTTP OK status.
        return new ResponseEntity<List<List<Reservation>>>(reservations, HttpStatus.OK);

    }

    @GetMapping("/get/{customerId}")
    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_MANAGER')" )
    public ResponseEntity<List<Reservation>> getreservationsByCustomerId(@PathVariable int customerId) {

        List<Reservation> reservations = reservationService.reservationsByCustomerId(customerId);


        return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);

    }
}