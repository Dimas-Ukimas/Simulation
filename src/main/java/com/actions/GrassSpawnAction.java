package com.actions;


import com.entities.Grass;
import com.map.WorldMap;

public class GrassSpawnAction extends EntitySpawnAction<Grass> {

    public GrassSpawnAction(WorldMap map) {
        double spawnRate = 0.14;
        currentEntityAmount = map.getEntitiesByClass(Grass.class).size();
        maxEntityAmount = (int) (map.getMapSize() * spawnRate);
        minEntityAmount = maxEntityAmount / 2;
    }

    @Override
    public Grass spawnEntity() {
        return new Grass();
    }
}
