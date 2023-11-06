package com;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class MenuController implements Runnable {

    private final int START_SIMULATION = 1;
    private final int PAUSE_SIMULATION = 2;
    private final int SWITCH_ENDLESS_MODE = 3;
    private final int EXIT = 0;
    private Simulation simulation;

    public MenuController(Simulation simulation) {
        this.simulation = simulation;
    }

    public MenuController() {
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (!Thread.interrupted()) {
            try {
                int userInput = scanner.nextInt();
                switch (userInput) {
                    case START_SIMULATION -> simulation.setPause(false);
                    case PAUSE_SIMULATION -> simulation.setPause(true);
                    case SWITCH_ENDLESS_MODE -> simulation.setEndlessMode(!simulation.isEndlessMode());
                    case EXIT -> simulation.setTerminated(true);
                    default -> System.out.println("Please, input a correct number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please, input a number.");
                scanner.next();
            }
        }
        scanner.close();
    }


    public String getMenuLine(int lineNumber) {
        String header = "Available commands:";
        String start = "Start simulation: " + "\"" + START_SIMULATION + "\"";
        String pause = "Pause simulation: " + "\"" + PAUSE_SIMULATION + "\"";
        String endlessMode = "Switch endless mode: " + "\"" + SWITCH_ENDLESS_MODE + "\"";
        String exit = "Exit: " + "\"" + EXIT + "\"";
        int headerLineNumber = 0;
        int startLineNumber = 2;
        int pauseLineNumber = 3;
        int endlessModeLineNumber = 4;
        int exitLineNumber = 5;

        Map<Integer, String> menuLines = new HashMap<>();
        menuLines.put(headerLineNumber, header);
        menuLines.put(startLineNumber, start);
        menuLines.put(pauseLineNumber, pause);
        menuLines.put(endlessModeLineNumber, endlessMode);
        menuLines.put(exitLineNumber, exit);

        String lineToPrint = null;

        for (Map.Entry<Integer, String> line : menuLines.entrySet()) {
            if (line.getKey().equals(lineNumber)) {
                lineToPrint = line.getValue();
            }
        }
        return lineToPrint;
    }
}
