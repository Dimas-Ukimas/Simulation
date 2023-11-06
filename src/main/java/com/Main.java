package com;

public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation();

        Thread thread1 = new Thread(new MenuController(simulation));
        thread1.setDaemon(true);
        thread1.start();

        simulation.startSimulation();
    }
}
