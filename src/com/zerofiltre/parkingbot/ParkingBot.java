package com.zerofiltre.parkingbot;

import com.zerofiltre.parkingbot.model.Bicycle;
import com.zerofiltre.parkingbot.model.Car;
import com.zerofiltre.parkingbot.model.Ticket;
import com.zerofiltre.parkingbot.model.Vehicle;
import com.zerofiltre.parkingbot.service.ParkingService;
import com.zerofiltre.parkingbot.util.Printer;

import java.util.ArrayList;
import java.util.List;

public class ParkingBot {

  public static final String REGISTRATION_NUMBER_1 = "LS-324-PM";
  public static final String REGISTRATION_NUMBER_2 = "PM-254-OP";
  public static final String REGISTRATION_NUMBER_3 = "BX-256-QX";
  static ParkingService parkingService = new ParkingService();
  static Printer printer;

  /**
   * Ceci est la méthode Main
   *
   * @param args : Tableau de données entrées lors du lancement de l'application
   */
  public static void main(String[] args) {
    printer = System.out::println;
    processVehicles();
  }

  private static void processVehicles() {
    List<Ticket> tickets = new ArrayList<>();

    Vehicle vehicle = new Vehicle();
    vehicle.setRegistrationNumber(REGISTRATION_NUMBER_1);
    Ticket vehicleTicket = parkingService.processIncomingVehicle(vehicle);
    printer.print(vehicleTicket);
    tickets.add(vehicleTicket);

    Vehicle bicycle = new Bicycle();
    bicycle.setRegistrationNumber(REGISTRATION_NUMBER_2);
    Ticket bicycleTicket = parkingService.processIncomingVehicle(bicycle);
    printer.print(bicycleTicket);
    tickets.add(bicycleTicket);

    Vehicle car = new Car();
    bicycle.setRegistrationNumber(REGISTRATION_NUMBER_3);
    Ticket carTicket = parkingService.processIncomingVehicle(car);
    printer.print(carTicket);
    tickets.add(carTicket);

    printer.print("Début du traitement de sorties en lot de " + tickets.size() + " véhicules");
    for (Ticket ticket : tickets) {
        try {
            printer.print(parkingService.processExitingVehicle(ticket));
        } catch (Exception e) {
            printer.print("Une erreur est survenue lors de la sortie d'un ou plusieurs véhicules");
        }
    }
    printer.print("Fin du traitement des sorties par lot");

  }


}
