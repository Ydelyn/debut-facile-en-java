package com.zerofiltre.parkingbot;

import java.security.SecureRandom;

public class ParkingBot {

  static String[] customers =
      {"Leye", "Ali", "Réné", "Booba", "Léa", "Hamza", "Philippe", "Zineb", "Jules", "Mukul", "Will"};

  static int parkingSize = new SecureRandom().nextInt(10);

  /**
   * Ceci est la méthode Main
   *
   * @param args : Tableau de données entrées lors du lancement de l'application
   */
  public static void main(String[] args) {
//    for (int i = 0; i < customers.length; i++) {
//      sayHello(customers[i]);
//    }

//    for (String customer : customers) {
//      sayHello(customer);
//    }

    System.out.println("Nous avons " + parkingSize + " place(s) disponible(s)");
    int customerNumber = 0;

//    while (parkingSize > 0){
//      parkingSize--;
//
//      if("Réné".equals(customers[customerNumber])){
//        customerNumber++;
//        parkingSize++;
//        continue;
//      }
//
//      if("Booba".equals(customers[customerNumber])){
//        break;
//      }
//
//      sayHello(customers[customerNumber]);
//      customerNumber++;
//    }

    do {
      parkingSize--;

      if("Réné".equals(customers[customerNumber])){
        customerNumber++;
        parkingSize++;
        continue;
      }

      if("Booba".equals(customers[customerNumber])){
        break;
      }

      sayHello(customers[customerNumber]);
      customerNumber++;
    } while (parkingSize > 0);
  }


  /**
   *
   * @param customer Le client à qui dire Hello
   */
  private static void sayHello(String customer) {
    String welcomeSentence = "Hello " + customer;
    System.out.println(welcomeSentence);
  }

}
