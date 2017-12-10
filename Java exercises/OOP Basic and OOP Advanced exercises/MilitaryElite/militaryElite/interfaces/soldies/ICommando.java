package militaryElite.interfaces.soldies;

import militaryElite.bonuses.Mission;

import java.util.List;

public interface ICommando {
    List<Mission> getMissions();

    void addMission(Mission mission);
}
