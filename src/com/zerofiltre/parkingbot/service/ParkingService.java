package com.zerofiltre.parkingbot.service;

import com.zerofiltre.parkingbot.model.Ticket;
import com.zerofiltre.parkingbot.model.Vehicle;
import com.zerofiltre.parkingbot.model.VehicleModelEnum;

import java.util.Date;

public class ParkingService {

  public Ticket processIncomingVehicle(Vehicle vehicle) {
    Ticket ticket = new Ticket();
    Date now = new Date();
    ticket.setEnteringTime(now);
    ticket.setVehicle(vehicle);
    return ticket;
  }

  public Ticket processExitingVehicle(Ticket ticket) {
    long now = new Date().getTime();
    now += 60 * 60 * 1000;

    Date exitTime = new Date(now);
    ticket.setExitTime(exitTime);

    Vehicle vehicle = ticket.getVehicle();
    VehicleModelEnum category = vehicle.getCategory();

    double pricePerMinCitadine = 0.08;
    double pricePerMinDeuxRoues = 0.02;
    double defaultPricePerMin = 0.05;

    long durationInMinutes = (ticket.getExitTime().getTime() - ticket.getEnteringTime().getTime()) / (1000 * 60);
    double finalPrice = 0;

//    if ("CITADINE".equals(category)) {
//      finalPrice = durationInMinutes * pricePerMinCitadine;
//    } else if ("2 ROUES".equals(category)) {
//      finalPrice = durationInMinutes * pricePerMinDeuxRoues;
//    } else {
//      finalPrice = durationInMinutes * defaultPricePerMin;
//    }

    switch (category) {
      case CITADINE:
        finalPrice = durationInMinutes * pricePerMinCitadine;
        break;
      case DEUXROUES:
        finalPrice = durationInMinutes * pricePerMinDeuxRoues;
        break;
      default:
        finalPrice = durationInMinutes * defaultPricePerMin;
    }

    ticket.setAmount(finalPrice);
    return ticket;
  }
}
