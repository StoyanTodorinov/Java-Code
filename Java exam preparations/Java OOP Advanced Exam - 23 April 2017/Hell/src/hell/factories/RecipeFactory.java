package hell.factories;

import hell.interfaces.Recipe;
import hell.entities.miscellaneous.items.RecipeItem;

public final class RecipeFactory {

    public static Recipe createRecipe(String name, int str, int agi, int intel, int health, int dmg, String[] requiredItems) {
        return new RecipeItem(name, str, agi, intel, health, dmg, requiredItems);
    }
}
