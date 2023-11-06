package com.map;

import java.util.ArrayList;
import java.util.List;

public class PathNode {

    private int heuristicTravelCostToTarget;
    private int travelCostToCell;
    private int generalCellCost;
    private final Cell parent;
    private final Cell currentCell;

    public PathNode(Cell parent, Cell currentCell) {
        this.parent = parent;
        this.currentCell = currentCell;
    }

    public int getHeuristicTravelCostToTarget() {
        return heuristicTravelCostToTarget;
    }

    public void setHeuristicTravelCostToTarget(int heuristicTravelCostToTarget) {
        this.heuristicTravelCostToTarget = heuristicTravelCostToTarget;
    }

    public int getTravelCostToCell() {
        return travelCostToCell;
    }

    public void setTravelCostToCell(int travelCostToCell) {
        this.travelCostToCell = travelCostToCell;
    }

    public int getGeneralCellCost() {
        return generalCellCost;
    }

    public void setGeneralCellCost(int generalCellCost) {
        this.generalCellCost = generalCellCost;
    }

    public Cell getParent() {
        return parent;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public List<PathNode> getOrthogonalCells(WorldMap map) {

        int[][] orthogonalSides = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        List<PathNode> sides = new ArrayList<>();

        for (int[] side : orthogonalSides) {
            Cell sideCell = new Cell(currentCell.getX() + side[0], currentCell.getY() + side[1]);
            if (map.isCellWithinBorders(sideCell.getX(), sideCell.getY())) {
                sides.add(new PathNode(currentCell, sideCell));
            }
        }
        return sides;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        PathNode c = (PathNode) o;
        return this.currentCell.equals(c.currentCell);
    }
}
