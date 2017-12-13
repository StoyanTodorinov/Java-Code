package hell.factories;

import hell.interfaces.Item;
import hell.entities.miscellaneous.items.CommonItem;

public final class ItemFactory {

    public static Item createCommonItem(String name, int str, int agi, int intel, int health, int dmg) {
        return new CommonItem(name, str, agi, intel, health, dmg);
    }
}
