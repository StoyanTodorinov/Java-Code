package hell.core.commands;

import hell.interfaces.Repository;

public class QuitCommand extends BaseCommand {

    @Override
    public String execute() {
        Repository repository = super.getRepository();
        return repository.toString();
    }
}
