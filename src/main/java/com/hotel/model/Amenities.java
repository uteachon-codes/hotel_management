package com.hotel.model;

import org.springframework.stereotype.Component;

@Component
public class Amenities {

    private boolean iron;
    private boolean fridge;

    public boolean isIron() {
        return iron;
    }

    public void setIron(boolean iron) {
        this.iron = iron;
    }

    public boolean isFridge() {
        return fridge;
    }

    public void setFridge(boolean fridge) {
        this.fridge = fridge;
    }

}