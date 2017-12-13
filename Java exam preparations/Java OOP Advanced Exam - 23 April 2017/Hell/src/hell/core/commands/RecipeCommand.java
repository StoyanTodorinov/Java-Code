package hell.core.commands;

import hell.interfaces.Repository;
import hell.factories.RecipeFactory;
import hell.interfaces.Recipe;

import java.util.List;

public class RecipeCommand extends BaseCommand {

    private static final String ADDED_RECIPE_TO_HERO_FORMAT = "Added recipe - %s to Hero - %s";

    @Override
    public String execute() {
        List<String> tokens = super.getData();
        String[] requiredItems = tokens.stream().skip(8).toArray(String[]::new);
        Repository repository = super.getRepository();
        Recipe recipe = RecipeFactory.createRecipe(tokens.get(1), Integer.parseInt(tokens.get(3)), Integer.parseInt(tokens.get(4))
                , Integer.parseInt(tokens.get(5)), Integer.parseInt(tokens.get(6)), Integer.parseInt(tokens.get(7)), requiredItems);
        repository.addRecipe(tokens.get(2), recipe);
        return String.format(ADDED_RECIPE_TO_HERO_FORMAT, tokens.get(1), tokens.get(2));
    }
}
