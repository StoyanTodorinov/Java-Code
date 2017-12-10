package itsInTheBlood.models;

import itsInTheBlood.interfaces.AbstractCell;
import itsInTheBlood.interfaces.AbstractCluster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Cluster implements AbstractCluster {

    private static final String ORGANISM_ACTIVATE_CLUSTER_RESPONSE_FORMAT = "Organism %s: Activated cluster %s. Cells left: %s\n";
    private static final Comparator<AbstractCell> CELL_COMPARATOR_ROWS = Comparator.comparingInt(AbstractCell::getPositionRow);
    private static final Comparator<AbstractCell> CELL_COMPARATOR_COLS = Comparator.comparingInt(AbstractCell::getPositionCol);;
    private static final String CLUSTER_STRING_REPRESENTATION = "----Cluster ";
    private static final String BLOOD_CELL_TYPE = "bloodcell";

    private String id;
    private int rows;
    private int cols;
    private List<AbstractCell> cells;

    public Cluster(String id, int rows, int cols) {
        this.id = id;
        this.rows = rows;
        this.cols = cols;
        this.cells = new ArrayList<>();
    }

    public List<AbstractCell> getCells() {
        return Collections.unmodifiableList(this.cells);
    }

    public void addCell(AbstractCell cell) {
        if (cell.getPositionRow() < rows && cell.getPositionCol() < cols) {
            this.cells.add(cell);
        }
    }

    public String activate(String organismName) {

        if (this.cells.size() == 1 || this.cells.size() == 0) {
            return String.format(ORGANISM_ACTIVATE_CLUSTER_RESPONSE_FORMAT, organismName, this.id, this.cells.size());
        }

        this.cells.sort(CELL_COMPARATOR_ROWS.thenComparing(CELL_COMPARATOR_COLS));

        AbstractCell winnerCell = null;

        for (AbstractCell cell : this.cells) {
            if (winnerCell == null) {
                winnerCell = cell;
                continue;
            }
            if (BLOOD_CELL_TYPE.equals(winnerCell.getCellType())) {
                winnerCell.increaseHealth(cell.getHealth());
            } else {
                while (true) {
                    cell.reduceHealth(winnerCell.getEnergy());
                    if (cell.isDead()) {
                        break;
                    }
                    winnerCell.reduceHealth(cell.getEnergy());
                    if (winnerCell.isDead()) {
                        winnerCell = cell;
                        break;
                    }
                }
            }
        }

        AbstractCell lastCellInTheCollection = this.cells.get(this.cells.size() - 1);
        winnerCell.setRowAndCol(lastCellInTheCollection.getPositionRow(), lastCellInTheCollection.getPositionCol());
        this.cells.clear();
        this.cells.add(winnerCell);

        return String.format(ORGANISM_ACTIVATE_CLUSTER_RESPONSE_FORMAT, organismName, this.id, this.cells.size());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(CLUSTER_STRING_REPRESENTATION).append(this.id).append(System.lineSeparator());
        this.cells.sort(CELL_COMPARATOR_ROWS.thenComparing(CELL_COMPARATOR_COLS));
        for (AbstractCell cell : this.cells) {
            sb.append(cell);
        }

        return sb.toString();
    }
}
