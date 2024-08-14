package com.zerofiltre.parkingbot.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.zerofiltre.parkingbot.model.Bicycle;
import com.zerofiltre.parkingbot.model.Car;
import com.zerofiltre.parkingbot.model.Ticket;
import com.zerofiltre.parkingbot.model.Vehicle;
import java.util.Date;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ParkingServiceTest {

  public static final String REGISTRATION_NUMBER = "AZ-458-56";
  public static final String CAR_REGISTRATION_NUMBER = "AA";
  private static final String BICYCLE_REGISTRATION_NUMBER = "GTFSHSJ";

  ParkingService parkingService = new ParkingService();
  private Vehicle vehicle;
  private Ticket ticket;
  private Date now;
  private Date enteringTime;


  @BeforeAll
  void init() {
    //given : Soit un véhicule a l'entrée du parking avec une plaque d'immatriculation X

    vehicle = new Vehicle();
    vehicle.setRegistrationNumber(REGISTRATION_NUMBER);

    ticket = new Ticket();
    ticket.setVehicle(vehicle);
  }

  @BeforeEach
  void reInit() {
    now = new Date();
    long nowMinus1Hour = now.getTime() - 60 * 60 * 1000;
    enteringTime = new Date(nowMinus1Hour);
    ticket.setEnteringTime(enteringTime);
  }

  @AfterEach
  void afterEach() {
    System.out.println("Je m'affiche à chaque fin de test");
  }

  @AfterAll
  void afterAll() {
    System.out.println("Je m'affiche à la fin de tous les tests");
  }

  @Timeout(2)
  @Test
  void failMoreThan2Seconds() {
    try {
      Thread.sleep(3 * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Test
  void givenAVehicle_processIncomingVehicle_generatesTicketWithRightTime() {
    //when : Enregistrer le véhicule
    Ticket ticket = parkingService.processIncomingVehicle(vehicle);

    //then:
    //génère un ticket,
    assertThat(ticket).isNotNull();

    //associé au véhicule de plaque x,
    Vehicle registeredVehicle = ticket.getVehicle();
    assertThat(registeredVehicle).isNotNull();
    String registeredRegistrationNumber = registeredVehicle.getRegistrationNumber();
    assertThat(registeredRegistrationNumber).isEqualTo(REGISTRATION_NUMBER);

    //avec comme date d'entrée la date actuelle à 30 secondes près
    Date date = ticket.getEnteringTime();
    assertThat(date).isNotNull();
    Date nowPlus30Seconds = new Date(now.getTime() + 30 * 1000);
    assertThat(date).isBeforeOrEqualTo(nowPlus30Seconds);
  }

  @Test
  void givenARegisteredVehicle_processExitingVehicle_generatesTicketWithHourAndPrice() {
    // when
    Ticket exitTicket = parkingService.processExitingVehicle(ticket);

    //then
    assertThat(exitTicket).isNotNull();
    Date exitTime = exitTicket.getExitTime();
    assertThat(exitTime).isNotNull();
    assertThat(exitTime).isAfter(ticket.getEnteringTime());
    assertThat(exitTicket.getAmount()).isNotEqualTo(0);
  }

  @Test
  void givenARegisteredVehicle_processExitingVehicle_generatesTheRightPrice() {
    Vehicle car = new Car();
    car.setRegistrationNumber(CAR_REGISTRATION_NUMBER);
    Ticket carTicket = new Ticket();
    carTicket.setVehicle(car);

    Vehicle bicycle = new Bicycle();
    bicycle.setRegistrationNumber(BICYCLE_REGISTRATION_NUMBER);
    Ticket bicycleTicket = new Ticket();
    bicycleTicket.setVehicle(bicycle);

    carTicket.setEnteringTime(enteringTime);
    bicycleTicket.setEnteringTime(enteringTime);

    // then
    Ticket exitVehicleTicket = parkingService.processExitingVehicle(ticket);
    Ticket exitCarTicket = parkingService.processExitingVehicle(carTicket);
    Ticket exitBicycleTicket = parkingService.processExitingVehicle(bicycleTicket);

    assertThat(exitVehicleTicket).isNotNull();
    assertThat(exitCarTicket).isNotNull();
    assertThat(exitBicycleTicket).isNotNull();

    assertThat(exitVehicleTicket.getAmount()).isEqualTo(3);
    assertThat(exitCarTicket.getAmount()).isEqualTo(4.8);
    assertThat(exitBicycleTicket.getAmount()).isEqualTo(1.2);
  }

}
