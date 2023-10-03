package com.hotel.exception;

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
