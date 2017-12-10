package itsInTheBlood;

import itsInTheBlood.cells.Cell;

import java.util.ArrayList;
import java.util.List;

public class Cluster {

    private String id;
    private int rows;
    private int cols;
    private List<Cell> cells;
    private Cell[][] cellMatrix;

    public Cluster(String id, int rows, int cols) {
        this.id = id;
        this.rows = rows;
        this.cols = cols;
        createClusterObjectMatrix(rows, cols);
        this.cells = new ArrayList<>();
    }

    private void createClusterObjectMatrix(int rows, int cols) {
        cellMatrix = new Cell[rows][cols];
    }

    public void addCell(Cell cell) {
        try {
            this.cellMatrix[cell.getPositionRow()][cell.getPositionCol()] = cell;
            this.cells.add(cell);
        } catch (IndexOutOfBoundsException ex) {
        }
    }

    public String getId() {
        return id;
    }

    public int getCellCount() {
        return this.cells.size();
    }

    public String returnStringOfAllCells() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.cellMatrix.length; i++) {
            for (int j = 0; j < this.cellMatrix[i].length; j++) {
                if (cellMatrix[i][j] != null) {
                    sb.append(String.format("------Cell %s [%d,%d]\n", cellMatrix[i][j].getId(), i, j));
                    sb.append(cellMatrix[i][j].toString());
                }
            }
        }

        return sb.toString();
    }

    public String activateCells(String organismName, String clusterId) {

        if (this.cells.size() == 1) {
            return String.format("Organism %s: Activated cluster %s. Cells left: %d\n"
                    , organismName, clusterId, this.cells.size());
        }

        boolean firstFound = false;
        Cell curCell = null;

        for (int i = 0; i < this.cellMatrix.length; i++) {
            for (int j = 0; j < this.cellMatrix[i].length; j++) {

                if (cellMatrix[i][j] != null && firstFound) {
                    Cell cellToFight = cellMatrix[i][j];

                    if (curCell.getType().equals("BloodCell")) {
                        curCell.increaseHealth(cellToFight.getHealth());
                        this.cells.remove(cellToFight);

                    } else {
                        while (true) {

                            cellToFight.reduceHealth(curCell.getEnergy());
                            if (cellToFight.getHealth() <= 0) {
                                this.cells.remove(cellToFight);
                                break;
                            }
                            curCell.reduceHealth(cellToFight.getEnergy());
                            if (curCell.getHealth() <= 0) {
                                curCell = cellToFight;
                                this.cells.remove(curCell);
                                break;
                            }
                        }
                    }
                }


                if (this.cells.size() == 1) {
                    curCell.setPositionRow(i);
                    curCell.setPositionCol(j);
                    this.cellMatrix = new Cell[this.rows][this.cols];
                    this.cellMatrix[i][j] = curCell;
                    this.cells.clear();
                    this.cells.add(curCell);
                    return String.format("Organism %s: Activated cluster %s. Cells left: %d\n"
                            , organismName, clusterId, 1);
                }

                if (cellMatrix[i][j] != null && !firstFound) {
                    curCell = cellMatrix[i][j];
                    firstFound = true;
                }
            }
        }
        return String.format("Organism %s: Activated cluster %s. Cells left: %d\n"
                , organismName, clusterId, this.cells.size());
    }
}