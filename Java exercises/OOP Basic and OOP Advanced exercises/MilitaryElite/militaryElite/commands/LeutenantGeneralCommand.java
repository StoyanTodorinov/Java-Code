package militaryElite.commands;

import militaryElite.soldiers.LeutenantGeneral;

public class LeutenantGeneralCommand extends Command {

    @Override
    public String execute() {
        String[] tokens = super.getData();
        LeutenantGeneral lg = new LeutenantGeneral(tokens[1], tokens[2], tokens[3], Double.parseDouble(tokens[4]));
        for (int i = 5; i < tokens.length; i++) {
            lg.getSetPrivates().add(super.getPrivates().get(tokens[i]));
        }
        return lg.toString();
    }
}
