package itsInTheBlood.factories;

import itsInTheBlood.models.Cluster;

public final class ClusterFactory {

    private ClusterFactory() {}

    public static Cluster createCluster(String id, int rows, int cols) {
        return new Cluster(id,rows,cols);
    }
}
