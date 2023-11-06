package com.actions;

import com.entities.Creature;
import com.map.Cell;
import com.map.WorldMap;

import java.util.Map;

public class CreaturesMoveAction extends Action {
    private int allCreaturesOnMap = 0;

    @Override
    public void perform(WorldMap map) {
        for (Class<? extends Creature> clazz : Creature.getHeirClasses()) {
            Map<Cell, ? extends Creature> creatureKindOnMap = map.getEntitiesByClass(clazz);
            allCreaturesOnMap += creatureKindOnMap.size();
            for (Map.Entry<Cell, ? extends Creature> e : creatureKindOnMap.entrySet()) {
                e.getValue().makeMove(e.getValue(), e.getKey(), map);
            }
        }
        if (allCreaturesOnMap != Creature.getPathlessCreaturesCounter()) {
            Creature.setPathlessCreaturesCounter(0);
        }
    }

    public boolean isAllCreaturesPathless(WorldMap map) {
        return allCreaturesOnMap == Creature.getPathlessCreaturesCounter() && map.isCreaturesTargetsExist();
    }
}
