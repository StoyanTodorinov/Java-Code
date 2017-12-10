package militaryElite.soldiers;

import militaryElite.bonuses.Mission;
import militaryElite.interfaces.soldies.ICommando;

import java.util.ArrayList;
import java.util.List;

public class Commando extends SpecialisedSoldier implements ICommando{
    private static final String IN_PROGRESS = "inProgress";
    private static final String FINISHED = "Finished";
    private static final String CORPS = "Corps: ";
    private static final String MISSIONS = "Missions:\n";

    private List<Mission> missions;

    public Commando(String id, String firstName, String lastName, double salary, String corps) {
        super(id, firstName, lastName, salary, corps);
        this.missions = new ArrayList<>();
    }

    @Override
    public List<Mission> getMissions() {
        return this.missions;
    }

    @Override
    public void addMission(Mission mission) {
        if (mission.getState().equals(FINISHED) || mission.getState().equals(IN_PROGRESS)) {
            this.missions.add(mission);
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append(CORPS).append(super.getCorps()).append("\n");
        sb.append(MISSIONS);
        for (Mission mission : missions) {
            sb.append(mission.toString());
        }

        return sb.toString();
    }
}
