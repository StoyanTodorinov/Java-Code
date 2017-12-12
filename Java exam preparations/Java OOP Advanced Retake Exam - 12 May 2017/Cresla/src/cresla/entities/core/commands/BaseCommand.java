package cresla.entities.core.commands;

import cresla.annotations.Inject;
import cresla.database.Repository;

import java.util.List;

public abstract class BaseCommand implements Executable, Command {

    @Inject
    private List<String> data;
    @Inject
    private Repository repository;

    protected BaseCommand() {
    }

    @Override
    public List<String> getData() {
        return this.data;
    }

    @Override
    public Repository getRepository() {
        return this.repository;
    }
}
