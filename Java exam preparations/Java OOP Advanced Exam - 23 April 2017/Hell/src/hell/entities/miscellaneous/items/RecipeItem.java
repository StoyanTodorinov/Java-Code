package hell.entities.miscellaneous.items;

import hell.interfaces.Recipe;

import java.util.Arrays;
import java.util.List;

public class RecipeItem extends BaseItem implements Recipe {

    private String[] requiredItems;

    public RecipeItem(String name, int strengthBonus, int agilityBonus, int intelligenceBonus, int hitPointsBonus, int damageBonus, String... requiredItems) {
        super(name, strengthBonus, agilityBonus, intelligenceBonus, hitPointsBonus, damageBonus);
        this.requiredItems = requiredItems;
    }

    @Override
    public List<String> getRequiredItems() {
        return Arrays.asList(this.requiredItems);
    }
}
