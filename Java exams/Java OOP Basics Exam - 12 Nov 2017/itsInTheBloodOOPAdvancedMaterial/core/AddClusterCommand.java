package itsInTheBlood.core;

import java.util.List;

public class AddClusterCommand extends BaseCommand {

    @Override
    public String execute() {
        List<String> data = super.getData();
        return super.getRepository().addCluster(data.get(0), data.get(1)
                , Integer.parseInt(data.get(2)), Integer.parseInt(data.get(3)));
    }
}
