package itsInTheBlood.interfaces;

public interface AbstractOrganism {
    String addCluster(String id ,AbstractCluster cluster);
    String addCell(String clusterId, String cellType, String cellId, int health
            , int positionRow, int positionCol, int additionalProperty);
    String activateCluster();
}
