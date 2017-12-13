package hell.entities.heroes;

import hell.entities.miscellaneous.HeroInventory;
import hell.entities.miscellaneous.ItemCollection;
import hell.enums.HeroTypes;
import hell.interfaces.Hero;
import hell.interfaces.Inventory;
import hell.interfaces.Item;
import hell.interfaces.Recipe;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class BaseHero implements Hero {
    private static final String HERO_NAME_AND_CLASS_FORMAT = "Hero: %s, Class: %s\n";
    private static final String HIT_POINTS_AND_DAMAGE_FORMAT = "HitPoints: %d, Damage: %d\n";
    private static final String STRENGTH_FORMAT = "Strength: %d\n";
    private static final String AGILITY_FORMAT = "Agility: %d\n";
    private static final String INTELLIGENCE_FORMAT = "Intelligence: %d\n";
    private static final String ITEMS = "Items:\n";
    private static final String ITEMS_NONE = "Items: None";
    private static final String ITEM_FORMAT = "###Item: %s\n";
    private static final String ITEM_STRENGTH_BONUS = "###+%d Strength\n";
    private static final String ITEM_AGILITY_BONUS = "###+%d Agility\n";
    private static final String ITEM_INTELLIGENCE_BONUS = "###+%d Intelligence\n";
    private static final String ITEM_HIT_POINTS_BONUS = "###+%d HitPoints\n";
    private static final String ITEM_DAMAGE_BONUS = "###+%d Damage\n";
    private static final String BARBARIAN = "Barbarian";
    private static final String ASSASSIN = "Assassin";

    private String name;
    private long strength;
    private long agility;
    private long intelligence;
    private long hitPoints;
    private long damage;
    private Inventory inventory;
    private Map<String, Item> items;

    BaseHero(String name, String heroType) {
        this.name = name;
        setStats(heroType);
        this.inventory = new HeroInventory();
        this.items = new LinkedHashMap<>();
    }

    private void setStats(String heroType) {
        HeroTypes hero = HeroTypes.valueOf(heroType.toUpperCase());
        this.strength = hero.getStrength();
        this.agility = hero.getAgility();
        this.intelligence = hero.getIntelligence();
        this.hitPoints = hero.getHitPoints();
        this.damage = hero.getDamage();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getStrength() {
        return this.strength;
    }

    @Override
    public long getAgility() {
        return this.agility;
    }

    @Override
    public long getIntelligence() {
        return this.intelligence;
    }

    @Override
    public long getHitPoints() {
        return this.hitPoints;
    }

    @Override
    public long getDamage() {
        return this.damage;
    }

    @Override
    public Collection<Item> getItems() {
        this.calculateBonuses();
        return this.items.values();
    }

    @Override
    public void addItem(Item item) {
        this.inventory.addCommonItem(item);
        this.calculateBonuses();
    }

    @Override
    public void addRecipe(Recipe recipe) {
        this.inventory.addRecipeItem(recipe);
        this.calculateBonuses();
    }

    @Override
    public String toString() {
        this.calculateBonuses();

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(HERO_NAME_AND_CLASS_FORMAT, this.name, this.getClass().getSimpleName()));
        sb.append(String.format(HIT_POINTS_AND_DAMAGE_FORMAT, this.hitPoints, this.damage));
        sb.append(String.format(STRENGTH_FORMAT, this.strength));
        sb.append(String.format(AGILITY_FORMAT, this.agility));
        sb.append(String.format(INTELLIGENCE_FORMAT, this.intelligence));
        String itemString = this.formatItems();
        sb.append(itemString);

        return sb.toString();
    }

    private String formatItems() {
        if (this.items.isEmpty()) {
            return ITEMS_NONE;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(ITEMS);
            for (Item item : this.items.values()) {
                sb.append(String.format(ITEM_FORMAT, item.getName()));
                sb.append(String.format(ITEM_STRENGTH_BONUS, item.getStrengthBonus()));
                sb.append(String.format(ITEM_AGILITY_BONUS, item.getAgilityBonus()));
                sb.append(String.format(ITEM_INTELLIGENCE_BONUS, item.getIntelligenceBonus()));
                sb.append(String.format(ITEM_HIT_POINTS_BONUS, item.getHitPointsBonus()));
                sb.append(String.format(ITEM_DAMAGE_BONUS, item.getDamageBonus()));
            }
            return sb.toString().trim();
        }
    }

    private void calculateBonuses() {

        Class<?> cl = this.inventory.getClass();
        Field[] fields = cl.getDeclaredFields();

        Map<String, Item> items = null;

        for (Field field : fields) {
            if (field.isAnnotationPresent(ItemCollection.class)) {
                field.setAccessible(true);
                try {
                    items = (Map<String, Item>) field.get(this.inventory);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        HeroTypes hero;

        if (this.getClass().getSimpleName().equals(BARBARIAN)) {
            hero = HeroTypes.BARBARIAN;
        } else if (this.getClass().getSimpleName().equals(ASSASSIN)) {
            hero = HeroTypes.ASSASSIN;
        } else {
            hero = HeroTypes.WIZARD;
        }

        this.strength = hero.getStrength();
        this.agility = hero.getAgility();
        this.intelligence = hero.getIntelligence();
        this.hitPoints = hero.getHitPoints();
        this.damage = hero.getDamage();

        this.items = items;

        if (items == null){
            return;
        }

        for (Item item : items.values()) {
            this.strength += item.getStrengthBonus();
            this.agility += item.getAgilityBonus();
            this.intelligence += item.getIntelligenceBonus();
            this.hitPoints += item.getHitPointsBonus();
            this.damage += item.getDamageBonus();
        }
    }
}
