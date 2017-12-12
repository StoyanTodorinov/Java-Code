package cresla.entities.core.commands;

import cresla.database.Repository;

import java.util.List;

public interface Command {
    List<String> getData();
    Repository getRepository();
}
