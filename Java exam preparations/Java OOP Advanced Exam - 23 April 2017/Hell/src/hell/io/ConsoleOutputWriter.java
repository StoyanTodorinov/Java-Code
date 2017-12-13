package hell.io;

import hell.interfaces.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class ConsoleOutputWriter implements OutputWriter {

    @Override
    public void writeLine(String output) {
        System.out.println(output);
    }

    @Override
    public void writeLine(String format, Object... params) {
        List<String> stringList = new ArrayList<>();
        for (Object param : params) {
            stringList.add(params[0].toString());
        }
        System.out.println(String.format(format, String.join(" ",stringList)));
    }
}
