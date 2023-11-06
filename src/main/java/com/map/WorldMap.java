package com.map;

import com.entities.Creature;
import com.entities.Entity;

import java.util.*;


public class WorldMap {

    private final Map<Cell, Entity> worldMap;
    private final int row;
    private final int column;

    public int getRowSize() {
        return row;
    }

    public int getColumnSize() {
        return column;
    }

    public WorldMap(int row, int column) {
        worldMap = new HashMap<>();
        this.row = row;
        this.column = column;
    }

    public int getMapSize() {
        return row * column;
    }

    public void placeEntity(Cell cell, Entity entity) {
        worldMap.put(cell, entity);
    }

    public void removeEntity(Cell cell) {
        worldMap.remove(cell);
    }

    public Entity getEntityByCell(Cell cell) {
        return worldMap.get(cell);
    }

    public boolean isCellWithinBorders(int x, int y) {
        boolean withinX = x >= 0 && x < row;
        boolean withinY = y >= 0 && y < column;

        return withinX && withinY;
    }

    public boolean isOccupiedCell(Cell cell) {
        return worldMap.containsKey(cell);
    }

    public <T extends Entity> Map<Cell, T> getEntitiesByClass(Class<T> objectClass) {
        Map<Cell, T> entities = new HashMap<>();
        for (Map.Entry<Cell, Entity> e : worldMap.entrySet()) {
            if (objectClass.isInstance(e.getValue())) {
                entities.put(e.getKey(), (T) e.getValue());
            }
        }
        return entities;
    }

    public boolean isCreaturesTargetsExist() {
        List<Boolean> targetsForAllCreaturesExist = new ArrayList<>();

        for (Class<? extends Entity> target : Creature.getTargetsClasses())
            for (Map.Entry<Cell, Entity> e : worldMap.entrySet()) {
                if (e.getValue().getClass().equals(target)) {
                    targetsForAllCreaturesExist.add(Boolean.TRUE);
                    break;
                }
            }
        return targetsForAllCreaturesExist.size() == Creature.getHeirClasses().size();
    }
}
