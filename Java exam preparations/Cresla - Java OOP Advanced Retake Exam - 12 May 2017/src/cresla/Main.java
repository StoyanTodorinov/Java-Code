package cresla;

import cresla.database.ReactorRepository;
import cresla.database.Repository;
import cresla.entities.core.engine.Engine;
import cresla.entities.io.ConsoleInputReader;
import cresla.entities.io.ConsoleOutputWriter;
import cresla.interfaces.InputReader;
import cresla.interfaces.OutputWriter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        InputReader inputReader = new ConsoleInputReader();
        OutputWriter outputWriter = new ConsoleOutputWriter();
        Repository repository = new ReactorRepository();
        Runnable engine = new Engine(inputReader, outputWriter, repository);
        engine.run();

    }
}
