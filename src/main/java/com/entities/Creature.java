package com.entities;

import com.map.Cell;
import com.map.PathFinder;
import com.map.WorldMap;

import java.util.*;

public abstract class Creature extends Entity {
    private int speed;
    private int hitPoints;
    private Class<? extends Entity> target;
    private static final Set<Class<? extends Creature>> heirClasses = new HashSet<>();
    private static final Set<Class<? extends Entity>> targetsClasses = new HashSet<>();

    public Creature() {
        heirClasses.add(this.getClass());
    }

    public static Set<Class<? extends Entity>> getTargetsClasses() {
        return targetsClasses;
    }

    public static void addTargetClass(Class<? extends Entity> targetClass) {
        Creature.targetsClasses.add(targetClass);
    }

    public static Set<Class<? extends Creature>> getHeirClasses() {
        return heirClasses;
    }

    public Class<? extends Entity> getTarget() {
        return target;
    }

    public void setTarget(Class<? extends Entity> target) {
        this.target = target;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public boolean makeMove(Creature subject, Cell startPosition, WorldMap map) {

        Map<Cell, Entity> adjacentTargets = getAdjacentTargets(startPosition, map, getTarget());

        if (!adjacentTargets.isEmpty()) {
            ArrayList<Cell> targetPositions = new ArrayList<>(adjacentTargets.keySet());
            Random rn = new Random();
            int randomNum = rn.nextInt(targetPositions.size());
            consume(targetPositions.get(randomNum), map);
            return true;
        } else {
            PathFinder pathFinder = new PathFinder();
            List<Cell> pathToTarget = pathFinder.findPath(startPosition, getTarget(), map);

            if (!pathToTarget.isEmpty()) {
                int maxStep = pathToTarget.size() - getSpeed();

                if (maxStep >= 0) {
                    map.moveEntity(startPosition, pathToTarget.get(getSpeed() - 1));
                } else {
                    map.moveEntity(startPosition, pathToTarget.get(pathToTarget.size() - 1));
                }
                return true;
            } else {
                return false;
            }
        }
    }

    public Map<Cell, Entity> getAdjacentTargets(Cell currentCell, WorldMap map, Class<? extends Entity> target) {

        Map<Cell, Entity> targets = map.getEntitiesByClass((Class<Entity>) target);
        Map<Cell, Entity> adjacentTargets = new HashMap<>();

        for (Map.Entry<Cell, Entity> e : targets.entrySet()) {
            int proximityByX = Math.abs(e.getKey().getX() - currentCell.getX());
            int proximityByY = Math.abs(e.getKey().getY() - currentCell.getY());

            if (proximityByX + proximityByY == 1) {
                adjacentTargets.put(e.getKey(), e.getValue());
            }
        }
        return adjacentTargets;
    }

    public abstract void consume(Cell targetLocation, WorldMap map);
}
