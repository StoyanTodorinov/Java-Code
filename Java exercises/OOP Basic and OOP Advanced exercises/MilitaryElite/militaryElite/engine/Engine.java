package militaryElite.engine;

import militaryElite.annotations.Inject;
import militaryElite.soldiers.Private;
import militaryElite.interfaces.core.Executable;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Engine implements Runnable {
    private static final String TERMINATING_COMMAND = "End";
    private static final String PACKAGE_ROUTE = "militaryElite.commands.";
    private static final String COMMAND = "Command";

    private BufferedReader reader;
    private String[] data;
    private Map<String, Private> privates;

    public Engine(BufferedReader reader) {
        this.reader = reader;
        this.privates = new HashMap<>();
    }

    @Override
    public void run() {
        while (true) {
            try {
                String[] tokens = reader.readLine().split(" ");
                if (tokens[0].equals(TERMINATING_COMMAND)) {
                    break;
                }
                this.data = tokens;
                Class<?> commandClass = Class.forName(PACKAGE_ROUTE + (tokens[0] + COMMAND));
                Constructor<?> declaredConstructor = commandClass.getDeclaredConstructor();
                Executable executable = (Executable) declaredConstructor.newInstance();
                this.injectDependencies(executable);
                printValue(executable.execute());

            } catch (IOException | ClassNotFoundException | IllegalAccessException
                    | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    private void printValue(String execute) {
        if (execute != null) {
            System.out.println(execute);
        }
    }

    private void injectDependencies(Executable executable) throws IllegalAccessException {
        Field[] fields = executable.getClass().getSuperclass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                Field[] currentFields = this.getClass().getDeclaredFields();
                for (Field currentField : currentFields) {
                    if (field.getType().equals(currentField.getType()) && field.getName().equals(currentField.getName())) {
                        field.setAccessible(true);
                        field.set(executable, currentField.get(this));
                    }
                }
            }
        }
    }
}
