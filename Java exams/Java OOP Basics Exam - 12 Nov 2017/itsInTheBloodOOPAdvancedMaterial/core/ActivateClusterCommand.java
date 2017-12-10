package itsInTheBlood.core;

public class ActivateClusterCommand extends BaseCommand {

    @Override
    public String execute() {
        return super.getRepository().activateCluster(super.getData().get(0));
    }
}
