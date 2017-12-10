package militaryElite.soldiers;

import militaryElite.interfaces.soldies.IPrivate;

public class Private extends Soldier implements IPrivate{
    private static final String NAME_S_S_ID_S_SALARY_2F = "Name: %s %s Id: %s Salary: %.2f\n";

    private double salary;

    public Private(String id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName);
        this.salary = salary;
    }

    @Override
    public double getSalary() {
        return this.salary;
    }

    @Override
    public String toString() {
        return String.format(NAME_S_S_ID_S_SALARY_2F
                , super.getFirstName(), super.getLastName(), super.getId(), this.salary);
    }
}
