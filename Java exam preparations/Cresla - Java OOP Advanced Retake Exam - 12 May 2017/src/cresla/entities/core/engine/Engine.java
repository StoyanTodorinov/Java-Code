package cresla.entities.core.engine;

import cresla.annotations.Inject;
import cresla.database.Repository;
import cresla.entities.core.commands.Executable;
import cresla.interfaces.InputReader;
import cresla.interfaces.OutputWriter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Engine implements Runnable {
    private static final String TERMINATE_COMMAND = "Exit";
    private static final String COMMAND_PACKAGE_ROUTE = "cresla.entities.core.commands.";
    private static final String COMMAND_SUFFIX = "Command";
    private static final String REACTOR = "Reactor";
    private static final String MODULE = "Module";

    private static int consequentIDs = 1;

    @Inject
    private List<String> data;
    @Inject
    private Repository repository;
    private InputReader reader;
    private OutputWriter writer;

    public Engine(InputReader inputReader, OutputWriter outputWriter, Repository repository) {
        this.reader = inputReader;
        this.writer = outputWriter;
        this.repository = repository;
    }

    @Override
    public void run() {
        while (true) {
            List<String> tokens = new ArrayList<>(Arrays.asList(this.reader.readLine().split(" ")));
            String command = null;
            try {
                command = tokens.remove(0);
                String result = this.dispatchCommand(command, tokens);
                this.writer.write(result);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
            if (TERMINATE_COMMAND.equals(command)) {
                break;
            }
        }
    }

    private String dispatchCommand(String command, List<String> tokens) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String fullCommand = command + COMMAND_SUFFIX;
        Class<?> cl = Class.forName(COMMAND_PACKAGE_ROUTE + fullCommand);
        Executable executable = (Executable) cl.newInstance();
        tokens.add(0, consequentIDs + "");
        if (command.equals(MODULE) || command.equals(REACTOR)) {
            consequentIDs++;
        }
        this.data = tokens;
        injectDependencies(executable);
        return executable.execute();
    }

    private void injectDependencies(Executable executable) throws IllegalAccessException {
        Field[] fields = executable.getClass().getSuperclass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                Field[] currentFields = this.getClass().getDeclaredFields();
                for (Field currentField : currentFields) {
                    if (field.getType().equals(currentField.getType())) {
                        field.setAccessible(true);
                        field.set(executable, currentField.get(this));
                    }
                }
            }
        }
    }
}
