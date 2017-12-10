package militaryElite.commands;

import militaryElite.bonuses.Repair;
import militaryElite.soldiers.Engineer;

public class EngineerCommand extends Command {

    @Override
    public String execute() {
        String[] tokens = this.getData();
        Engineer eng;
        try {
            eng = new Engineer(tokens[1], tokens[2], tokens[3], Double.parseDouble(tokens[4]), tokens[5]);
        } catch (IllegalArgumentException ex) {
            return null;
        }
        for (int i = 6; i < tokens.length; i += 2) {
            eng.addRepair(new Repair(tokens[i], Integer.parseInt(tokens[i + 1])));
        }
        return eng.toString();
    }
}
