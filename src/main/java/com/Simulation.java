package com;



import com.actions.*;
import com.map.Render;
import com.map.WorldMap;

import java.util.*;

public class Simulation {

    private final WorldMap map = new WorldMap(15, 15);
    private final Render render = new Render(map);
    private final CreaturesMoveAction moveCreatures = new CreaturesMoveAction();
    private int turnCounter;
    private boolean endlessMode = false;
    private volatile boolean isPaused = false;
    private volatile boolean isTerminated = false;


    public Simulation() {
        populateMap(map);
    }

    public boolean isOver() {
        return !endlessMode && !map.isCreaturesTargetsExist();
    }

    public void setPause(boolean pause) {
        this.isPaused = pause;
    }

    public void setTerminated(boolean terminated) {
        this.isTerminated = terminated;
    }

    public boolean isEndlessMode() {
        return endlessMode;
    }

    public void setEndlessMode(boolean endlessMode) {
        this.endlessMode = endlessMode;
    }

    public void startSimulation() {
        while (!isOver() && !isTerminated) {
            render.setSimulationMenuLines(getLinesToPrint());
            nextTurn();
            if (moveCreatures.isAllCreaturesPathless(map)) {
                break;
            }
        }
        render.setSimulationMenuLines(getLinesToPrint());
        render.setSimulationClosingLines(getClosingLinesToPrint());
        render.renderSimulation();
    }

    public void populateMap(WorldMap map) {
        List<Action> spawnActions = new ArrayList<>();
        spawnActions.add(new GrassSpawnAction(map));
        spawnActions.add(new HerbivoreSpawnAction(map));
        spawnActions.add(new PredatorSpawnAction(map));
        spawnActions.add(new RockSpawnAction(map));
        spawnActions.add(new TreeSpawnAction(map));

        for (Action spawner : spawnActions) {
            spawner.perform(map);
        }
    }

    public void nextTurn() {
        if (!isPaused) {
            render.renderSimulation();
            if (endlessMode) {
                populateMap(map);
            }
            moveCreatures.perform(map);
            turnCounter++;
        } else {
            render.renderSimulation();
        }
    }

    public Map<Integer, String> getLinesToPrint() {
        String turnCount = "Current turn: " + turnCounter + " ";
        String endlessModeOn = "Endless mode: ON";
        String endlessModeOff = "Endless mode: OFF";
        int turnCountLineNumber = 8;
        int endlessModeOnLineNumber = 7;
        int endlessModeOffLineNumber = 7;

        Map<Integer, String> linesToPrint = new HashMap<>();

        if (endlessMode) {
            linesToPrint.put(endlessModeOnLineNumber, endlessModeOn);
        } else {
            linesToPrint.put(endlessModeOffLineNumber, endlessModeOff);
        }
        linesToPrint.put(turnCountLineNumber, turnCount);

        return linesToPrint;
    }

    public Set<String> getClosingLinesToPrint() {
        String simulationIsOver = "Simulation is over. There are no more targets for one kind of creatures.";
        String simulationIsTerminated = "Simulation is terminated.";
        String noPathForCreatures = "Simulation is over. Creatures have no available paths to targets.";
        Set<String> closingLinesToPrint = new HashSet<>();

        if (isOver()) {
            closingLinesToPrint.add(simulationIsOver);
        }
        if (isTerminated) {
            closingLinesToPrint.add(simulationIsTerminated);
        }
        if (moveCreatures.isAllCreaturesPathless(map)) {
            closingLinesToPrint.add(noPathForCreatures);
        }
        return closingLinesToPrint;
    }
}
