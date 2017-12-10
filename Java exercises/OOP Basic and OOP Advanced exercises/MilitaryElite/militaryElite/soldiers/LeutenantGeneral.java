package militaryElite.soldiers;

import militaryElite.interfaces.soldies.ILeutenentGeneral;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LeutenantGeneral extends Private implements ILeutenentGeneral {

    public static final String PRIVATES = "Privates:\n";
    private List<Private> setOfPrivates;

    public LeutenantGeneral(String id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName, salary);
        this.setOfPrivates = new ArrayList<>();
    }

    @Override
    public List<Private> getSetPrivates() {
        return this.setOfPrivates;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append(PRIVATES);
        for (Private setOfPrivate :
                setOfPrivates.stream().sorted((a, b)-> b.getId().compareTo(a.getId())).collect(Collectors.toList())) {
            sb.append("  ").append(setOfPrivate.toString());
        }

        return sb.toString();
    }
}
