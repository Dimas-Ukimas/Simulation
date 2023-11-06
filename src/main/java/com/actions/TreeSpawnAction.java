package com.actions;


import com.entities.Tree;
import com.map.WorldMap;


public class TreeSpawnAction extends EntitySpawnAction<Tree> {

    public TreeSpawnAction(WorldMap map) {
        double spawnRate = 0.08;
        currentEntityAmount = map.getEntitiesByClass(Tree.class).size();
        maxEntityAmount = (int) (map.getMapSize() * spawnRate);
        minEntityAmount = maxEntityAmount / 4;
    }

    @Override
    public Tree spawnEntity() {
        return new Tree();
    }
}
