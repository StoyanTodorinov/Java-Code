package itsInTheBlood.models;

import itsInTheBlood.factories.CellFactory;
import itsInTheBlood.interfaces.AbstractCell;
import itsInTheBlood.interfaces.AbstractCluster;
import itsInTheBlood.interfaces.AbstractOrganism;

import java.util.LinkedHashMap;
import java.util.Map;

public class Organism implements AbstractOrganism {

    private static final String CLUSTER_CREATION_FORMAT = "Organism %s: Created cluster %s\n";
    private static final String CELL_CREATION_FORMAT = "Organism %s: Created cell %s in cluster %s\n";
    private static final String ORGANISM_STRING = "Organism - ";
    private static final String CLUSTERS_REPRESENTATION_FORMAT = "--Clusters: %d\n";
    private static final String CELLS_REPRESENTATION_FORMAT = "--Cells: %d\n";

    private String name;
    private Map<String, AbstractCluster> clusters;

    public Organism(String name) {
        this.name = name;
        this.clusters = new LinkedHashMap<>();
    }

    public String addCluster(String id, AbstractCluster cluster) {
        if (!this.clusters.containsKey(id)) {
            this.clusters.put(id, cluster);
            return String.format(CLUSTER_CREATION_FORMAT, this.name, id);
        }

        return null;
    }

    public String addCell(String clusterId, String cellType, String cellId, int health
            , int positionRow, int positionCol, int additionalProperty) {
        AbstractCell cell = CellFactory.createCell(cellType, cellId, health, positionRow, positionCol, additionalProperty);
        if (cell != null && this.clusters.containsKey(clusterId)) {
            this.clusters.get(clusterId).addCell(cell);
            return String.format(CELL_CREATION_FORMAT, this.name, cellId, clusterId);
        }

        return null;
    }

    public String activateCluster() {
        if (this.clusters.size() != 0) {
            Map.Entry<String, AbstractCluster> clusterAndId = this.clusters.entrySet().stream().findFirst().get();
            AbstractCluster cluster = clusterAndId.getValue();
            this.clusters.remove(clusterAndId.getKey());
            this.clusters.put(clusterAndId.getKey(), clusterAndId.getValue());
            return cluster.activate(this.name);
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(ORGANISM_STRING).append(this.name).append(System.lineSeparator());
        sb.append(String.format(CLUSTERS_REPRESENTATION_FORMAT, this.clusters.size()));
        sb.append(String.format(CELLS_REPRESENTATION_FORMAT, this.getAllCellsCount()));
        for (AbstractCluster cluster : this.clusters.values()) {
            sb.append(cluster);
        }

        return sb.toString();
    }

    private int getAllCellsCount() {
        int count = 0;
        for (AbstractCluster cluster : this.clusters.values()) {
            count += cluster.getCells().size();
        }
        return count;
    }
}
