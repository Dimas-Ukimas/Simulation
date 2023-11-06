package com.map;

import com.MenuController;
import com.entities.Entity;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class Render {

    MenuController menuController;
    Map<Integer, String> simulationLines;
    Set<String> simulationClosingLines;
    WorldMap map;


    public Render(WorldMap map) {
        this.menuController = new MenuController();
        this.map = map;
    }

    public void renderSimulation() {

        for (int i = 0; i < map.getRowSize(); i++) {
            for (int j = 0; j < map.getColumnSize(); j++) {
                Cell cell = new Cell(i, j);
                if (map.isOccupiedCell(cell)) {
                    Entity e = map.getEntityByCell(cell);
                    System.out.print(e.displayIcon());
                } else System.out.print("_");
            }

            if (menuController.getMenuLine(i) != null) {
                System.out.print(menuController.getMenuLine(i).indent(map.getColumnSize() + 5));
            } else if (simulationLines.get(i) != null) {
                System.out.print(simulationLines.get(i).indent(map.getColumnSize() + 5));
            } else {
                System.out.print("\n");
            }
        }
        System.out.println();

        if (simulationClosingLines == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clearConsole();
        } else {
            for (String line : simulationClosingLines)
                System.out.println("\n" + line);
        }
    }

    public void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void setSimulationMenuLines(Map<Integer, String> lines) {
        this.simulationLines = lines;
    }

    public void setSimulationClosingLines(Set<String> closingLines) {
        this.simulationClosingLines = closingLines;
    }

}
