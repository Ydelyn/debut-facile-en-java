package com.zerofiltre.parkingbot;

import com.zerofiltre.parkingbot.model.Bicycle;
import com.zerofiltre.parkingbot.model.Car;
import com.zerofiltre.parkingbot.model.Ticket;
import com.zerofiltre.parkingbot.model.Vehicle;
import com.zerofiltre.parkingbot.service.ParkingService;

import java.util.*;

public class ParkingBot {

  static ParkingService parkingService = new ParkingService();

  /**
   * Ceci est la méthode Main
   *
   * @param args : Tableau de données entrées lors du lancement de l'application
   */
  public static void main(String[] args) {
    processVehicles();
  }

  private static void processVehicles() {

    Set<Ticket> tickets = new HashSet<>();

    Vehicle vehicle = new Vehicle();
    vehicle.setRegistrationNumber("LS-324-PM");
    Ticket vehicleTicket = parkingService.processIncomingVehicle(vehicle);
    tickets.add(vehicleTicket);
    System.out.println(vehicleTicket);

    Vehicle bicycle = new Bicycle();
    bicycle.setRegistrationNumber("PM-254-OP");
    Ticket bicycleTicket = parkingService.processIncomingVehicle(bicycle);
    tickets.add(bicycleTicket);
    System.out.println(bicycleTicket);

    Vehicle car = new Car();
    bicycle.setRegistrationNumber("BX-256-QX");
    Ticket carTicket = parkingService.processIncomingVehicle(car);
    tickets.add(carTicket);
    System.out.println(carTicket);

//    Ticket[] tickets = new Ticket[3];
//    tickets[0] = vehicleTicket;
//    tickets[1] = bicycleTicket;
//    tickets[2] = carTicket;

    System.out.println("Traitement des sorties par lot");

    Map<Integer, Ticket> exitOrder = new HashMap<>();
    int position = 0;

    for (Ticket ticket : tickets) {
      exitOrder.put(position, ticket);
      position++;
      System.out.println(parkingService.processExitingVehicle(ticket));
    }

//    for (Ticket ticket : tickets) {
//        System.out.println(parkingService.processExitingVehicle(ticket));
//    }

    Set<Integer> keySet = exitOrder.keySet();
    for (int key : keySet) {
      System.out.println("Position dans la liste : " + key + " : " + exitOrder.get(key).getVehicle().getCategory());
    }
  }
}
