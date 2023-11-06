package com.map;

import com.entities.Entity;

import java.util.*;


public class PathFinder {

    public List<Cell> findPath(Cell startPosition, Class<? extends Entity> target, WorldMap map) {

        Map<Cell, Entity> allTargetsOnMap = map.getEntitiesByClass((Class<Entity>) target);
        List<ArrayList<Cell>> pathsToAllTargets = new ArrayList<>();

        List<PathNode> cellsToCheck = new ArrayList<>();
        List<PathNode> checkedCells = new ArrayList<>();
        PathNode startNode = new PathNode(startPosition, startPosition);
        cellsToCheck.add(startNode);

        for (Map.Entry<Cell, Entity> currentTarget : allTargetsOnMap.entrySet()) {

            boolean pathNotFound = true;

            while (pathNotFound) {
                if (!cellsToCheck.isEmpty()) {
                    int minGeneralCellCost = cellsToCheck.get(0).getGeneralCellCost();
                    PathNode nodeToCheck = cellsToCheck.get(0);

                    for (PathNode e : cellsToCheck) {
                        if (e.getGeneralCellCost() < minGeneralCellCost) {
                            minGeneralCellCost = e.getGeneralCellCost();
                            nodeToCheck = e;
                        }
                    }
                    cellsToCheck.remove(nodeToCheck);
                    checkedCells.add(nodeToCheck);

                    List<PathNode> adjacentCells = nodeToCheck.getOrthogonalCells(map);

                    for (PathNode p : adjacentCells) {
                        if (p.getCurrentCell().equals(currentTarget.getKey())) {
                            ArrayList<Cell> pathToCurrentTarget = new ArrayList<>();

                            while (!nodeToCheck.equals(startNode)) {
                                for (PathNode checkedNode : checkedCells) {
                                    if (nodeToCheck.getParent().equals(checkedNode.getCurrentCell())) {
                                        pathToCurrentTarget.add(nodeToCheck.getCurrentCell());
                                        nodeToCheck = checkedNode;
                                    }
                                }
                            }
                            pathsToAllTargets.add(pathToCurrentTarget);
                            cellsToCheck.clear();
                            checkedCells.clear();
                            cellsToCheck.add(startNode);
                            pathNotFound = false;
                            break;
                        }
                        if (!map.isOccupiedCell(p.getCurrentCell()) && !checkedCells.contains(p)) {
                            int targetX = currentTarget.getKey().getX();
                            int targetY = currentTarget.getKey().getY();
                            int pathNodeX = p.getCurrentCell().getX();
                            int pathNodeY = p.getCurrentCell().getY();

                            p.setHeuristicTravelCostToTarget((Math.abs(pathNodeX - targetX) + Math.abs(pathNodeY - targetY)) * 10);
                            p.setTravelCostToCell(nodeToCheck.getTravelCostToCell() + 10);
                            p.setGeneralCellCost(p.getTravelCostToCell() + p.getHeuristicTravelCostToTarget());

                            if (cellsToCheck.contains(p)) {
                                if (cellsToCheck.get(cellsToCheck.indexOf(p)).getGeneralCellCost() > p.getGeneralCellCost()) {
                                    cellsToCheck.remove(p);
                                    cellsToCheck.add(p);
                                }
                            } else {
                                cellsToCheck.add(p);
                            }
                        }
                    }
                } else {
                    cellsToCheck.add(startNode);
                    checkedCells.clear();
                    pathNotFound = false;
                }
            }
        }
        ArrayList<Cell> shortestPath = new ArrayList<>();

        if (!pathsToAllTargets.isEmpty()) {
            shortestPath = pathsToAllTargets.get(0);
            for (ArrayList<Cell> path : pathsToAllTargets) {
                if (shortestPath.size() > path.size()) {
                    shortestPath = path;
                }
            }
        }
        Collections.reverse(shortestPath);
        return shortestPath;
    }
}
