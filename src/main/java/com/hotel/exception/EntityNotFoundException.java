package com.hotel.exception;


/**
 * EntityNotFoundException is a custom exception class that extends RuntimeException.
 * It is used to indicate that a customer with a specified ID was not found in the system.
 * This exception is typically thrown when attempting to retrieve or manipulate customer data.
 *
 * @author Abdul Basith
 */
public class EntityNotFoundException extends RuntimeException {

    private String message;
    private int entityId;

    public EntityNotFoundException(String message, int entityId) {
        this.message = message;
        this.entityId = entityId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EntityNotFoundException{");
        sb.append("message='").append(message).append('\'');
        sb.append(", entityId=").append(entityId);
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

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }
}
