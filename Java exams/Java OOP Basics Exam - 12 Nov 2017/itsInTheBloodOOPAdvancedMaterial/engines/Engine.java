package itsInTheBlood.engines;

import itsInTheBlood.annotations.Inject;
import itsInTheBlood.interfaces.*;
import itsInTheBlood.interfaces.Runnable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Engine implements Runnable {

    private static final String SUFFIX = "Command";
    private static final String ROUTE = "itsInTheBlood.core.";
    private static final String TERMINATING_COMMAND = "BEER IS COMING";
    private static final String SPLITTER = " ";

    @Inject
    private List<String> data;
    @Inject
    private Repository repository;
    private InputReader reader;
    private OutputWriter writer;

    public Engine(Repository repository, InputReader reader, OutputWriter writer) {
        this.repository = repository;
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public void run() {

        while (true) {
            String line = reader.readLine();
            if (TERMINATING_COMMAND.equals(line)) {
                break;
            }

            String[] tokens = line.split(SPLITTER);
            try {
                this.dispatchCommand(tokens);
            } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException
                    | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void dispatchCommand(String[] tokens) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String command = tokens[0].toUpperCase().charAt(0) + tokens[0].substring(1);
        Class<Executable> commandClass = (Class<Executable>) Class.forName(ROUTE + command + SUFFIX);
        Constructor<Executable> constructorExecutable = commandClass.getDeclaredConstructor();
        Executable executable = constructorExecutable.newInstance();
        this.setData(tokens);
        this.injectDecencies(executable);
        String result = executable.execute();
        if (result != null) {
            writer.write(result);
        }
    }

    private void injectDecencies(Executable executable) throws IllegalAccessException {
        Field[] fields = executable.getClass().getSuperclass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                Field[] engineFields = this.getClass().getDeclaredFields();
                for (Field engineField : engineFields) {
                    if (engineField.isAnnotationPresent(Inject.class)) {
                        if (field.getType().equals(engineField.getType())) {
                            field.setAccessible(true);
                            field.set(executable, engineField.get(this));
                        }
                    }
                }
            }
        }
    }

    private void setData(String[] tokens) {
        this.data = new ArrayList<>(Arrays.stream(tokens).skip(1).collect(Collectors.toList()));
    }
}
