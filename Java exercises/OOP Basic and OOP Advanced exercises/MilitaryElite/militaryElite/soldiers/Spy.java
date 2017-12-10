package militaryElite.soldiers;

public class Spy extends Soldier {
    private static final String NAME_S_S_ID_S_CODE_NUMBER_S = "Name: %s %s Id: %s\nCode Number: %s\n";

    private String codeNumber;

    public Spy(String id, String firstName, String lastName, String codeNumber) {
        super(id, firstName, lastName);
        this.codeNumber = codeNumber;
    }

    @Override
    public String toString() {
        return String.format(NAME_S_S_ID_S_CODE_NUMBER_S
                , super.getFirstName(), super.getLastName(), super.getId(), this.codeNumber);
    }
}
