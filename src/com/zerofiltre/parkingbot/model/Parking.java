package com.zerofiltre.parkingbot.model;

import java.util.ArrayList;
import java.util.List;

public class Parking {
    List<Parking> subParking = new ArrayList<>();
    Vehicle vehicle;
    int number;
    ParkingTypeEnum type;

    public List<Parking> getSubParking() {
        return subParking;
    }

    public void setSubParking(List<Parking> subParking) {
        this.subParking = subParking;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ParkingTypeEnum getType() {
        return type;
    }

    public void setType(ParkingTypeEnum type) {
        this.type = type;
    }
}
