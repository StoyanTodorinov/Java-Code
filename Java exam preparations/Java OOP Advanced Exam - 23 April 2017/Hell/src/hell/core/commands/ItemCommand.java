package hell.core.commands;

import hell.interfaces.Repository;
import hell.factories.ItemFactory;
import hell.interfaces.Item;

import java.util.List;

public class ItemCommand extends BaseCommand {

    private static final String ADDED_ITEM_TO_HERO_FORMAT = "Added item - %s to Hero - %s";

    @Override
    public String execute() {
        List<String> tokens = super.getData();
        Repository repository = super.getRepository();
        Item item = ItemFactory.createCommonItem(tokens.get(1), Integer.parseInt(tokens.get(3)), Integer.parseInt(tokens.get(4))
                , Integer.parseInt(tokens.get(5)), Integer.parseInt(tokens.get(6)), Integer.parseInt(tokens.get(7)));
        repository.addItem(tokens.get(2), item);
        return String.format(ADDED_ITEM_TO_HERO_FORMAT, tokens.get(1), tokens.get(2));
    }
}
