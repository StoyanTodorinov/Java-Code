package itsInTheBlood.interfaces;

import java.util.List;

public interface AbstractCluster {
    List<AbstractCell> getCells();
    void addCell(AbstractCell cell);
    String activate(String organismName);
}
