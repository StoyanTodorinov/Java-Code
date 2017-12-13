package hell.core.commands;

import hell.interfaces.Repository;

import java.util.List;

public class InspectCommand extends BaseCommand {

    @Override
    public String execute() {
        List<String> tokens = super.getData();
        Repository repository = super.getRepository();
        return repository.inspect(tokens.get(1));
    }
}
