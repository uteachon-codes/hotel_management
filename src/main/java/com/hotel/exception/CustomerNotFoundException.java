package com.hotel.exception;


/**
 * CustomerNotFoundException is a custom exception class that extends RuntimeException.
 * It is used to indicate that a customer with a specified ID was not found in the system.
 * This exception is typically thrown when attempting to retrieve or manipulate customer data.
 *
 * @author Abdul Basith
 */
public class CustomerNotFoundException extends RuntimeException{

    private String message;
    private int customerId;
    public CustomerNotFoundException(String message,int customerId){
        this.message = message;
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomerNotFoundException{");
        sb.append("message='").append(message).append('\'');
        sb.append(", customerId='").append(customerId).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
