package itsInTheBlood.database;

import itsInTheBlood.factories.ClusterFactory;
import itsInTheBlood.factories.OrganismFactory;
import itsInTheBlood.interfaces.AbstractCluster;
import itsInTheBlood.interfaces.AbstractOrganism;
import itsInTheBlood.interfaces.Repository;

import java.util.HashMap;
import java.util.Map;

public class HealthManager implements Repository {

    private static final String CREATED_ORGANISM = "Created organism %s\n";
    private static final String ORGANISM_ALREADY_EXISTS = "Organism %s already exists\n";

    private Map<String, AbstractOrganism> organisms;

    public HealthManager() {
        this.organisms = new HashMap<>();
    }

    public String checkCondition(String organismName) {
        if (this.organisms.containsKey(organismName)) {
            return this.organisms.get(organismName).toString();
        }
        return null;
    }

    public String createOrganism(String name) {
        if (!this.organisms.containsKey(name)) {
            AbstractOrganism organism = OrganismFactory.createOrganism(name);
            this.organisms.put(name, organism);
            return String.format(CREATED_ORGANISM, name);
        }

        return String.format(ORGANISM_ALREADY_EXISTS, name);
    }

    public String addCluster(String organismName, String id, int rows, int cols) {
        if (this.organisms.containsKey(organismName)) {
            AbstractCluster cluster = ClusterFactory.createCluster(id,rows,cols);
            return this.organisms.get(organismName).addCluster(id, cluster);
        }

        return null;
    }

    public String addCell(String organismName, String clusterId, String cellType, String
            cellId, int health, int positionRow, int positionCol, int additionalProperty) {
        if (this.organisms.containsKey(organismName)) {
            return this.organisms.get(organismName).addCell(
                    clusterId, cellType, cellId, health, positionRow, positionCol, additionalProperty);
        }
        return null;
    }

    public String activateCluster(String organismName) {
        if (this.organisms.containsKey(organismName)) {
            return this.organisms.get(organismName).activateCluster();
        }

        return null;
    }
}
