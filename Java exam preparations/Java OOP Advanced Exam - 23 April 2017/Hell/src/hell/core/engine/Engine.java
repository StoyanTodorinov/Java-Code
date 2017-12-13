package hell.core.engine;

import hell.core.commands.Executable;
import hell.interfaces.Repository;
import hell.interfaces.InputReader;
import hell.interfaces.OutputWriter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Engine implements Runnable {
    private static final String TERMINATE_COMMAND = "Quit";
    private static final String COMMAND_CLASS_PATH = "hell.core.commands.";
    private static final String COMMAND_CLASS_NAME_SUFFIX = "Command";

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
            List<String> tokens = new ArrayList<>(Arrays.asList(this.reader.readLine().split("\\s+")));
            try {
                String result = this.dispatchCommand(tokens.get(0), tokens);
                writer.writeLine(result);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
            if (TERMINATE_COMMAND.equals(tokens.get(0))) {
                break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private String dispatchCommand(String command, List<String> tokens) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        this.data = tokens;
        Class<Executable> commandClass = (Class<Executable>) Class.forName(COMMAND_CLASS_PATH + command + COMMAND_CLASS_NAME_SUFFIX);
        Executable executable = commandClass.newInstance();
        injectDependencies(executable);
        return executable.execute();
    }

    private void injectDependencies(Executable executable) throws IllegalAccessException {
        Field[] fields = executable.getClass().getSuperclass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                Field[] thisFields = this.getClass().getDeclaredFields();
                for (Field thisField : thisFields) {
                    if (field.getType().equals(thisField.getType())) {
                        field.setAccessible(true);
                        field.set(executable, thisField.get(this));
                    }
                }
            }
        }
    }
}
