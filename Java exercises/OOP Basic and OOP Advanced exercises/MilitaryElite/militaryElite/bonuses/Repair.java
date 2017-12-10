package militaryElite.bonuses;

public class Repair {
    private static final String PART_NAME_S_HOURS_WORKED_D = "  Part Name: %s Hours Worked: %d\n";

    private String partName;
    private int hoursWorked;

    public Repair(String partName, int hoursWorked) {
        this.partName = partName;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public String toString() {
        return String.format(PART_NAME_S_HOURS_WORKED_D, this.partName, this.hoursWorked);
    }
}
