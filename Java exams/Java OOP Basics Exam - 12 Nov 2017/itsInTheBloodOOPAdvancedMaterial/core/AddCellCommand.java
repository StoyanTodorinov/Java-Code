package itsInTheBlood.core;

import java.util.List;

public class AddCellCommand extends BaseCommand {

    @Override
    public String execute() {
        List<String> data = super.getData();
        return super.getRepository().addCell(data.get(0),data.get(1),data.get(2),data.get(3), Integer.parseInt(data.get(4)),
                Integer.parseInt(data.get(5)), Integer.parseInt(data.get(6)), Integer.parseInt(data.get(7)));
    }
}
