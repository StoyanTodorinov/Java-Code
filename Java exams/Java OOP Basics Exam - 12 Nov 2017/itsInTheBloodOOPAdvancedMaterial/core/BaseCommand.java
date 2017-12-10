package itsInTheBlood.core;

import itsInTheBlood.annotations.Inject;
import itsInTheBlood.interfaces.Executable;
import itsInTheBlood.interfaces.Repository;

import java.util.List;

public abstract class BaseCommand implements Executable {

    @Inject
    private List<String> data;
    @Inject
    private Repository repository;

    protected BaseCommand() {
    }

    protected List<String> getData() {
        return this.data;
    }

    protected Repository getRepository() {
        return this.repository;
    }
}
