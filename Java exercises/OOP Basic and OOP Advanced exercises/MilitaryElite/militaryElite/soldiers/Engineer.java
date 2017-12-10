package militaryElite.soldiers;

import militaryElite.bonuses.Repair;
import militaryElite.interfaces.soldies.IEngineer;

import java.util.ArrayList;
import java.util.List;

public class Engineer extends SpecialisedSoldier implements IEngineer{
    private static final String CORPS = "Corps: ";
    private static final String REPAIRS = "Repairs:\n";

    public List<Repair> repairs;

    public Engineer(String id, String firstName, String lastName, double salary, String corps) {
        super(id, firstName, lastName, salary, corps);
        this.repairs = new ArrayList<>();
    }


    @Override
    public void addRepair(Repair repair) {
        this.repairs.add(repair);
    }

    @Override
    public List<Repair> getRepairs() {
        return this.repairs;
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        sb.append(super.toString());
        sb.append(CORPS).append(super.getCorps()).append("\n");
        sb.append(REPAIRS);
        for (Repair repair : repairs) {
            sb.append(repair.toString());
        }

        return sb.toString();
    }
}
