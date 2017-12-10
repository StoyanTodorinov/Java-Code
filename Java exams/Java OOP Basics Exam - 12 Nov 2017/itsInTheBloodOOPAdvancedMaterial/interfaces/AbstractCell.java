package itsInTheBlood.interfaces;

public interface AbstractCell {
    boolean isDead();
    void reduceHealth(int value);
    void setRowAndCol(int row, int col);
    void increaseHealth(int value);
    int getHealth();
    int getPositionCol();
    int getPositionRow();
    int getEnergy();
    String getCellType();
}
