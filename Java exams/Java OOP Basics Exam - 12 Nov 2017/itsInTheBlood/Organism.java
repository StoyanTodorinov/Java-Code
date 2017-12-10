package itsInTheBlood;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Organism {

    private String name;
    private Map<String, Cluster> clusters;

    public Organism(String name) {
        this.name = name;
        this.clusters = new LinkedHashMap<>();
    }

    public void addCluster(Cluster cluster) {
        this.clusters.putIfAbsent(cluster.getId(), cluster);
    }

    public String activateCluster() {
        if (!this.clusters.isEmpty()) {
            Cluster cluster = this.clusters.values().stream().findFirst().get();
            String result = cluster.activateCells(this.name, cluster.getId());
            this.clusters.remove(cluster.getId());
            this.clusters.put(cluster.getId(), cluster);
            return result;
        }
        return null;
    }

    public int getClusterCount() {
        return this.clusters.size();
    }

    public Map<String, Cluster> getClusters() {
        return Collections.unmodifiableMap(this.clusters);
    }

    public int getCellsCount() {
        int count = 0;
        for (Cluster cluster : clusters.values()) {
            count += cluster.getCellCount();
        }
        return count;
    }
}
