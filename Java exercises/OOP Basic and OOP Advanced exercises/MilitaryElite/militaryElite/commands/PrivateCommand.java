package militaryElite.commands;

import militaryElite.soldiers.Private;


public class PrivateCommand extends Command {

    @Override
    public String execute() {
        String[] tokens = super.getData();
        Private priv = new Private(tokens[1], tokens[2], tokens[3], Double.parseDouble(tokens[4]));
        super.getPrivates().put(tokens[1], priv);
        return priv.toString();
    }
}
