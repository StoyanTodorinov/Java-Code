package pr0304Barracks.core.commands;

import pr0304Barracks.contracts.Repository;
import pr0304Barracks.contracts.UnitFactory;

public class RetireCommand extends Command {

    public RetireCommand(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        String[] data = getData();
        String unitType = data[1];
        if (!this.getRepository().containsUnit(unitType)) {
            return "No such units in repository.";
        }
        this.getRepository().removeUnit(unitType);
        return unitType + " retired!";
    }
}
