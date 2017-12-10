package itsInTheBlood.core;

public class CheckConditionCommand extends BaseCommand {

    @Override
    public String execute() {
        return super.getRepository().checkCondition(super.getData().get(0));
    }
}
