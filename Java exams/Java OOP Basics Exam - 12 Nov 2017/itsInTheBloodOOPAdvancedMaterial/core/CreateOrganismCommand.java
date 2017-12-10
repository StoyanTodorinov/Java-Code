package itsInTheBlood.core;

public class CreateOrganismCommand extends BaseCommand {

    @Override
    public String execute() {
        return super.getRepository().createOrganism(super.getData().get(0));
    }
}
