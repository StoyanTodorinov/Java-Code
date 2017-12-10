package militaryElite.commands;

import militaryElite.soldiers.Spy;

public class SpyCommand extends Command {

   @Override
    public String execute() {
        String[] tokens = super.getData();
        Spy spy = new Spy(tokens[1], tokens[2], tokens[3], tokens[4]);
        return spy.toString();
    }
}
