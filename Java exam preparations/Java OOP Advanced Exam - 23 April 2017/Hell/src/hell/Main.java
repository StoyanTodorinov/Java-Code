package hell;

import hell.core.engine.Engine;
import hell.database.HeroDatabase;
import hell.interfaces.Repository;
import hell.interfaces.InputReader;
import hell.interfaces.OutputWriter;
import hell.io.ConsoleInputReader;
import hell.io.ConsoleOutputWriter;

public class Main {

    public static void main(String[] args) {
        Repository repository = new HeroDatabase();
        InputReader reader = new ConsoleInputReader();
        OutputWriter writer = new ConsoleOutputWriter();
        Runnable engine = new Engine(repository, reader, writer);
        engine.run();
    }
}