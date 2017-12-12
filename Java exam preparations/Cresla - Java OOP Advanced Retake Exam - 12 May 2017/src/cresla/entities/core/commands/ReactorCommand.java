package cresla.entities.core.commands;

import cresla.database.Repository;
import cresla.factories.ReactorFactory;
import cresla.interfaces.Reactor;

import java.util.List;

public class ReactorCommand extends BaseCommand {

    private static final String CREATED_REACTOR_FORMAT = "Created %s Reactor - %d\n";

    @Override
    public String execute() {
        Repository repository = super.getRepository();
        List<String> tokens = super.getData();

        Reactor reactor = ReactorFactory.createReactor(tokens.get(1), Integer.parseInt(tokens.get(0))
                , Integer.parseInt(tokens.get(2)), Integer.parseInt(tokens.get(3)));

        repository.addReactor(reactor);
        return String.format(CREATED_REACTOR_FORMAT, tokens.get(1), reactor.getId());
    }
}
