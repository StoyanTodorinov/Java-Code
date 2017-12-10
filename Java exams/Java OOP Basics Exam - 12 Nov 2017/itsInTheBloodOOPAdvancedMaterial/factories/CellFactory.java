package itsInTheBlood.factories;

import itsInTheBlood.interfaces.AbstractCell;
import itsInTheBlood.models.Cell;
import itsInTheBlood.models.bloodcells.RedBloodCell;
import itsInTheBlood.models.bloodcells.WhiteBloodCell;
import itsInTheBlood.models.microbes.Bacteria;
import itsInTheBlood.models.microbes.Fungi;
import itsInTheBlood.models.microbes.Virus;

public final class CellFactory {

    private static final String WHITE_BLOOD_CELL = "WhiteBloodCell";
    private static final String RED_BLOOD_CELL = "RedBloodCell";
    private static final String BACTERIA = "Bacteria";
    private static final String FUNGI = "Fungi";
    private static final String VIRUS = "Virus";

    private CellFactory() {}

    public static AbstractCell createCell(String cellType, String cellId, int health
            , int positionRow, int positionCol, int additionalProperty) {

        AbstractCell cell = null;

        switch (cellType) {

            case WHITE_BLOOD_CELL:
                cell = new WhiteBloodCell(cellId, health, positionRow, positionCol, additionalProperty);
                break;

            case RED_BLOOD_CELL:
                cell = new RedBloodCell(cellId, health, positionRow, positionCol, additionalProperty);
                break;

            case BACTERIA:
                cell = new Bacteria(cellId, health, positionRow, positionCol, additionalProperty);
                break;

            case FUNGI:
                cell = new Fungi(cellId, health, positionRow, positionCol, additionalProperty);
                break;

            case VIRUS:
                cell = new Virus(cellId, health, positionRow, positionCol, additionalProperty);
                break;

        }

        return cell;
    }
}
