package militaryElite.bonuses;

import militaryElite.interfaces.soldies.IMission;

public class Mission implements IMission {
    private static final String FINISHED = "Finished";
    private static final String CODE_NAME_S_STATE_S = "  Code Name: %s State: %s\n";

    private String codeName;
    private String state;

    public Mission(String codeName, String state) {
        this.codeName = codeName;
        setState(state);
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void completeMission() {
        this.state = FINISHED;
    }

    @Override
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return String.format(CODE_NAME_S_STATE_S, this.codeName, this.state);
    }
}
