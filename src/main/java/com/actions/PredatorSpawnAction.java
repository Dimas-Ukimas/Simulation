package com.actions;


import com.entities.Predator;
import com.map.WorldMap;


public class PredatorSpawnAction extends EntitySpawnAction<Predator> {

    public PredatorSpawnAction(WorldMap map) {
        double spawnRate = 0.04;
        currentEntityAmount = map.getEntitiesByClass(Predator.class).size();
        maxEntityAmount = (int) (map.getMapSize() * spawnRate);
        minEntityAmount = maxEntityAmount / 2;
    }

    @Override
    public Predator spawnEntity() {
        return new Predator();
    }


}
