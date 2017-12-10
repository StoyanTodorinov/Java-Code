package militaryElite.interfaces.soldies;

import militaryElite.bonuses.Repair;

import java.util.List;

public interface IEngineer {
    void addRepair(Repair repair);

    List<Repair> getRepairs();
}
