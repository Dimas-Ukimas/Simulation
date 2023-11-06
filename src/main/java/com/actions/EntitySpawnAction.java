package com.actions;


import com.entities.Entity;
import com.map.Cell;
import com.map.WorldMap;


public abstract class EntitySpawnAction<T extends Entity> extends Action {

    protected int currentEntityAmount;
    protected int maxEntityAmount;
    protected int minEntityAmount;

    public void perform(WorldMap map) {
        if (currentEntityAmount < minEntityAmount) {
            while (currentEntityAmount < maxEntityAmount) {
                map.placeEntity(getRandomEmptyCoordinates(map), spawnEntity());
                currentEntityAmount++;
            }
        }
    }

    public Cell getRandomEmptyCoordinates(WorldMap map) {
        Cell cell;
        do {
            int x = (int) (Math.random() * map.getRowSize());
            int y = (int) (Math.random() * map.getColumnSize());
            cell = new Cell(x, y);
        } while (map.isOccupiedCell(cell));
        return cell;
    }

    public abstract T spawnEntity();

}








