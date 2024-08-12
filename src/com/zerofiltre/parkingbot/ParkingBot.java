package com.zerofiltre.parkingbot;

public class ParkingBot {

    /**
     * This is the main function of a JAVA program
     * @param args represents data gived in parameter when the program is launched
     */
    public static void main(String[] args) {
        sayHello(args);
    }

    /**
     * Allow to welcome and to present our services to the potential user of the parking
     * @param args represents the name of the potential user
     */
    private static void sayHello(String[] args) {
        String welcomeMessage = "Hello "+ args[0] + ", bienvenue au parking Zerofiltre";
        String services = "Nous offrons les services suivants : Gardiennage, laverie ...";
        String warning = "Dépechez-vous d'entrer car il n'y aura bientôt plus de places !";
        System.out.println(welcomeMessage);
        System.out.println(services);
        System.out.println(warning.toUpperCase());
    }
}
