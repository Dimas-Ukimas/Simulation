package com.entities;

import com.map.Cell;
import com.map.WorldMap;

public class Herbivore extends Creature {

    public Herbivore() {
        setSpeed(2);
        setHitPoints(10);
        setTarget(Grass.class);
        addTargetClass(Grass.class);
    }

    @Override
    public void consume(Cell targetLocation, WorldMap map) {
        map.removeEntity(targetLocation);
    }

    @Override
    public String displayIcon() {
        return "H";
    }
}

