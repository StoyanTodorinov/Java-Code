package hell.core.commands;

import hell.core.engine.Inject;
import hell.interfaces.Repository;

import java.util.List;

public abstract class BaseCommand implements Executable {

    @Inject
    private List<String> data;
    @Inject
    private Repository repository;

    BaseCommand() {
    }

    List<String> getData() {
        return this.data;
    }

    protected Repository getRepository() {
        return this.repository;
    }
}
