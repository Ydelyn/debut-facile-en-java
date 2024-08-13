package com.zerofiltre.parkingbot;

import com.zerofiltre.parkingbot.model.Ticket;
import com.zerofiltre.parkingbot.model.Vehicle;
import com.zerofiltre.parkingbot.service.ParkingService;

public class ParkingBot {


  /**
   * Ceci est la méthode Main
   *
   * @param args : Tableau de données entrées lors du lancement de l'application
   */
  public static void main(String[] args) {
    processVehicles();
  }

  private static void processVehicles() {
    Vehicle vehicle = new Vehicle();
    vehicle.setRegistrationNumber("LS-324-PM");
    vehicle.setCategory("CITADINE");
    ParkingService parkingService = new ParkingService();
    Ticket ticket = parkingService.processIncomingVehicle(vehicle);
    System.out.println(ticket);
  }


}
