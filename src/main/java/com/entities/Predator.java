package com.entities;

import com.map.Cell;
import com.map.WorldMap;

public class Predator extends Creature {

    private int attackPower;

    public Predator() {
        setSpeed(3);
        setHitPoints(10);
        setAttackPower(5);
        setTarget(Herbivore.class);
        Creature.addTargetClass(Herbivore.class);
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    @Override
    public void consume(Cell targetLocation, WorldMap map) {
        Creature target = (Creature) map.getEntityByCell(targetLocation);

        if (target.getHitPoints() <= getAttackPower()) {
            map.removeEntity(targetLocation);
        } else target.setHitPoints(target.getHitPoints() - getAttackPower());
    }

    @Override
    public String displayIcon() {
        return "P";
    }
}

