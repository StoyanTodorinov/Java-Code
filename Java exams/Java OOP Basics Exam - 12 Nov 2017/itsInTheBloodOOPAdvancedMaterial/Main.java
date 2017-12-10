package itsInTheBlood;

import itsInTheBlood.database.HealthManager;
import itsInTheBlood.engines.Engine;
import itsInTheBlood.interfaces.InputReader;
import itsInTheBlood.interfaces.OutputWriter;
import itsInTheBlood.interfaces.Runnable;
import itsInTheBlood.io.ConsoleInputReader;
import itsInTheBlood.io.ConsoleOutputWriter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        HealthManager hm = new HealthManager();
        InputReader reader = new ConsoleInputReader();
        OutputWriter writer = new ConsoleOutputWriter();
        Runnable engine = new Engine(hm, reader, writer);
        engine.run();
    }
}
