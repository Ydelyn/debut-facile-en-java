package com.zerofiltre.parkingbot;


import com.zerofiltre.parkingbot.model.Parking;
import com.zerofiltre.parkingbot.model.Vehicle;
import com.zerofiltre.parkingbot.service.ParkingService;

import java.util.List;

import static com.zerofiltre.parkingbot.model.ParkingTypeEnum.SPOT;

public class ParkingBot {

  static ParkingService parkingService = new ParkingService();

  /**
   * Ceci est la méthode Main
   *
   * @param args : Tableau de données entrées lors du lancement de l'application
   */
  public static void main(String[] args) {
    Parking parking = parkingService.initParking();
    listCars(parking);
  }

  static void listCars(Parking parking) {
    if (SPOT.equals(parking.getType())) {
      Vehicle vehicle = parking.getVehicle();
      System.out.println("Vehicule d'immatriculation " + vehicle.getRegistrationNumber() + " situé à la place " + vehicle.getParkingSpotNumber());
    } else {
      List<Parking> subParkings = parking.getSubParking();
      for (Parking subParking : subParkings) {
        listCars(subParking);
      }
    }

  }

}
