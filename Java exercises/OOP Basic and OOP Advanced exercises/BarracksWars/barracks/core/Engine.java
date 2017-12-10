package pr0304Barracks.core;

import pr0304Barracks.contracts.*;
import pr0304Barracks.contracts.Runnable;
import pr0304Barracks.core.commands.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

public class Engine implements Runnable {
    private static final String DEFAULT_COMMAND_PACKAGE_ROUTE = "pr0304Barracks.core.commands.";

    private Repository repository;
    private UnitFactory unitFactory;

    public Engine(Repository repository, UnitFactory unitFactory) {
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            try {
                String input = reader.readLine();
                String[] data = input.split("\\s+");
                String commandName = data[0];
                String result = interpretCommand(data, commandName);
                if (result.equals("fight")) {
                    break;
                }
                System.out.println(result);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            } catch (IOException | IllegalAccessException
                    | ClassNotFoundException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private String interpretCommand(String[] data, String commandName) throws IllegalAccessException, ClassNotFoundException, InstantiationException, InvocationTargetException {

        String className = Character.toUpperCase(commandName.charAt(0)) + commandName.substring(1) + "Command";

        Class command = Class.forName(
                DEFAULT_COMMAND_PACKAGE_ROUTE + className);


        Executable executable = (Executable) command
                .getDeclaredConstructors()[0]
                .newInstance(data, this.repository, this.unitFactory);
        return executable.execute();
    }
}
