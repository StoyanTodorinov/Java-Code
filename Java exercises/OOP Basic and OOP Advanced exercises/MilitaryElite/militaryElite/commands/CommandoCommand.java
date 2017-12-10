package militaryElite.commands;

import militaryElite.bonuses.Mission;
import militaryElite.soldiers.Commando;

public class CommandoCommand extends Command {

    @Override
    public String execute() {
        String[] tokens = this.getData();
        Commando cmd;
        try {
            cmd = new Commando(tokens[1], tokens[2], tokens[3], Double.parseDouble(tokens[4]), tokens[5]);
        } catch (IllegalArgumentException ex) {return null;}
        for (int i = 6; i < tokens.length; i += 2) {
            cmd.addMission(new Mission(tokens[i], tokens[i + 1]));
        }
        return cmd.toString();
    }
}
