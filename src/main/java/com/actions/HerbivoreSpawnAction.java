package com.actions;

import com.entities.Herbivore;
import com.map.WorldMap;

public class HerbivoreSpawnAction extends EntitySpawnAction<Herbivore> {

    public HerbivoreSpawnAction(WorldMap map) {
        double spawnRate = 0.08;
        currentEntityAmount = map.getEntitiesByClass(Herbivore.class).size();
        maxEntityAmount = (int) (map.getMapSize() * spawnRate);
        minEntityAmount = maxEntityAmount / 2;
    }

    @Override
    public Herbivore spawnEntity() {
        return new Herbivore();
    }

}
