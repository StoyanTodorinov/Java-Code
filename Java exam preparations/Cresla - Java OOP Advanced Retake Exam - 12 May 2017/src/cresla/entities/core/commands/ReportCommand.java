package cresla.entities.core.commands;

import cresla.database.Repository;

import java.util.List;

public class ReportCommand extends BaseCommand {

    @Override
    public String execute() {
        Repository repository = super.getRepository();
        List<String> tokens = super.getData();
        return repository.reportItem(Integer.parseInt(tokens.get(1)));
    }
}
