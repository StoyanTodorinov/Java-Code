package itsInTheBlood.io;

import itsInTheBlood.interfaces.OutputWriter;

public class ConsoleOutputWriter implements OutputWriter {

    @Override
    public void write(String text) {
        System.out.print(text);
    }

    @Override
    public void writeLine(String text) {
        System.out.println(text);
    }
}
