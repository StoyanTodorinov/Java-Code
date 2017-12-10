package itsInTheBlood;

import itsInTheBlood.cells.Cell;
import itsInTheBlood.cells.bloodcells.RedBloodCell;
import itsInTheBlood.cells.bloodcells.WhiteBloodCell;
import itsInTheBlood.cells.microbes.Bacteria;
import itsInTheBlood.cells.microbes.Fungi;
import itsInTheBlood.cells.microbes.Virus;

import java.util.LinkedHashMap;
import java.util.Map;

public class HealthManager {

    private Map<String, Organism> organisms;

    public HealthManager() {
        this.organisms = new LinkedHashMap<>();
    }


    public String checkCondition(String organismName) {
        if (!this.organisms.containsKey(organismName)) {
            return null;
        }

        Organism organism = this.organisms.get(organismName);

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Organism - %s\n", organismName));
        sb.append(String.format("--Clusters: %d\n", organism.getClusterCount()));
        sb.append(String.format("--Cells: %d\n", organism.getCellsCount()));

        for (String clusterId : organism.getClusters().keySet()) {
            sb.append(String.format("----Cluster %s\n", clusterId));
            String allCells = organism.getClusters().get(clusterId).returnStringOfAllCells();
            sb.append(allCells);
        }

        return sb.toString();
    }

    public String createOrganism(String name) {
        if (this.organisms.containsKey(name)) {
            return String.format("Organism %s already exists\n", name);
        }
        this.organisms.put(name, new Organism(name));
        return String.format("Created organism %s\n", name);
    }

    public String addCluster(String organismName, String id, int rows, int cols) {
        if (!organisms.containsKey(organismName)) {
            return null;
        }
        if (this.organisms.get(organismName).getClusters().containsKey(id)) {
            return null;
        }
        Cluster cluster = new Cluster(id, rows, cols);
        this.organisms.get(organismName).addCluster(cluster);
        return String.format("Organism %s: Created cluster %s\n", organismName, cluster.getId());
    }

    public String addCell(String organismName, String clusterId, String cellType
            , String cellId, int health, int positionRow, int positionCol, int additionalProperty) {
        Cell cell = null;

        switch (cellType) {
            case "Virus":
                cell = new Virus(cellId, health, positionRow, positionCol, additionalProperty);
                break;
            case "Fungi":
                cell = new Fungi(cellId, health, positionRow, positionCol, additionalProperty);
                break;
            case "Bacteria":
                cell = new Bacteria(cellId, health, positionRow, positionCol, additionalProperty);
                break;
            case "WhiteBloodCell":
                cell = new WhiteBloodCell(cellId, health, positionRow, positionCol, additionalProperty);
                break;
            case "RedBloodCell":
                cell = new RedBloodCell(cellId, health, positionRow, positionCol, additionalProperty);
                break;
        }

        if (this.organisms.containsKey(organismName)) {
            if (this.organisms.get(organismName).getClusters().containsKey(clusterId)) {
                this.organisms.get(organismName).getClusters().get(clusterId).addCell(cell);
            } else {
                return null;
            }
        } else {
            return null;
        }

        return String.format("Organism %s: Created cell %s in cluster %s\n", organismName, cellId, clusterId);
    }

    public String activateCluster(String organismName) {
        if (this.organisms.containsKey(organismName)) {
            return this.organisms.get(organismName).activateCluster();
        }
        return null;
    }
}
