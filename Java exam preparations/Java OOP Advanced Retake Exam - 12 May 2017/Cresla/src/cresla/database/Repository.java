package cresla.database;

import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.EnergyModule;
import cresla.interfaces.Reactor;

public interface Repository {
    void addReactor(Reactor reactor);
    void addEnergyModule(int id, EnergyModule module);
    void addAbsorbingModule(int id, AbsorbingModule module);
    String reportItem(int id);
}
