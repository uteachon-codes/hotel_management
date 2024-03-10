package com.hotel.service.impl;

import com.hotel.exception.EntityNotFoundException;
import com.hotel.model.Customer;
import com.hotel.model.Reservation;
import com.hotel.repository.ReservationRepository;
import com.hotel.service.CustomerService;
import com.hotel.service.ReservationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {



    private ReservationRepository reservationRepository;

    private CustomerService customerService;

    private static final Logger logger = LogManager.getLogger(ReservationServiceImpl.class);

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository,CustomerService customerService) {
        this.reservationRepository = reservationRepository;
        this.customerService = customerService;
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        try {
        	
            Date currentDate = new Date();
            reservation.setReservationDate(currentDate);
            Reservation savedReservation = reservationRepository.save(reservation);
            logger.info("Saved Reservation with ID : "+savedReservation.getId());
            return savedReservation;
        }catch(Exception e){


            logger.error("Issue in saving the reservation ",e);
            throw e;
        }
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getReservationBetweenCheckInDates(Date startDate, Date endDate) {
        return reservationRepository.findByCheckInDateBetween(startDate, endDate);
    }


    /*
     *  getReservationCustomerPartialFirstName() takes the input as the partial first name of the guest checks if there is any reservation made for the given guest
     *
     * */
    @Override
    public List<List<Reservation>> getReservationCustomerPartialFirstName(String partialName) {

        // customersList stores the customers details if any customer name is present with the given partialName
        List<Customer> customersList = customerService.getCustomerByPartialFirstName(partialName);

        // anyReservationAvailable is a flag used to check if the returned customer names with the given partial names have at least a reservation otherwise we need to throw exception stating the customer name is found but not
        // reservations of that guest have been found

        boolean anyReservationAvailable = false;

        // In this code block we are checking if there is a customer name found for the given partial name, We cannot directly find the reservations with the given customer name, we first need to find the customerId
        // then using that customer id need to look for customers.
        // otherwise we will throw exception stating that the customer details not found with the given customer partial name
        if (!(customersList.isEmpty())) {

            // iniitializing the arraylist to store the customer all reservations
            List<List<Reservation>> reservationsList = new ArrayList<>();

            // This  for loop checks if there is any reservations made by the customers and stores the reservations in the reservationsList
            for (Customer customer : customersList) {
                int customerId = customer.getId();

                // reservationRepository.findByCustomerId is a method used for finding the reservations given the customer

                List<Reservation> reservationByCustomerId = reservationRepository.findByCustomerId(customerId);
                // Here we are checking if there is any reservations made the returned customer. if there is a single reservation made by the  customer. We will make this flag true otherwise it will remain false
                if (!reservationByCustomerId.isEmpty()) {
                    anyReservationAvailable = true;
                }
                reservationsList.add(reservationByCustomerId);
            }
            // Returning the reservations only if there is any reservation found by using the particular partialName and customerId
            if (anyReservationAvailable) {
                return reservationsList;
            } else {
                // Throwing the exception due to the customer with partialName found but that customer does not have reservations
                throw new EntityNotFoundException("Reservation Not Found with the Given Customer name " + partialName, 1005);
            }
        } else {
            // Throwing the exception because the customer details  not found with the given partialName
            throw new EntityNotFoundException("Customer Not Found with the given details", 1003);
        }

    }

    @Override
    public List<Reservation> reservationsByCustomerId(int customerId) {

        return reservationRepository.findByCustomerId(customerId);
    }
}