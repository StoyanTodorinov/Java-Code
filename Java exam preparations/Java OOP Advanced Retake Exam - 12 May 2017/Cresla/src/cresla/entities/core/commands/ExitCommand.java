package cresla.entities.core.commands;

import cresla.database.Repository;

public class ExitCommand extends BaseCommand {

    @Override
    public String execute() {
        Repository repository = super.getRepository();
        return repository.toString();
    }
}

