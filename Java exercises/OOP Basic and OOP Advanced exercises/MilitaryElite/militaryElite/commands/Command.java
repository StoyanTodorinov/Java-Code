package militaryElite.commands;

import militaryElite.annotations.Inject;
import militaryElite.soldiers.Private;
import militaryElite.interfaces.core.Executable;

import java.util.Map;

public abstract class Command implements Executable {

    @Inject
    private String[] data;
    @Inject
    private Map<String, Private> privates;

    protected Command() {
    }

    protected String[] getData() {
        return this.data;
    }

    protected Map<String, Private> getPrivates() {
        return this.privates;
    }
}
