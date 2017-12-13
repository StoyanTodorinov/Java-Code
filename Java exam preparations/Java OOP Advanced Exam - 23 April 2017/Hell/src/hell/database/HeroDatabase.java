package hell.database;

import hell.interfaces.Hero;
import hell.interfaces.Item;
import hell.interfaces.Recipe;
import hell.interfaces.Repository;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HeroDatabase implements Repository {
    private static final String HERO_CLASS_AND_NAME_FORMAT = "%d. %s: %s\n";
    private static final String HIT_POINTS_FORMAT = "###HitPoints: %d\n";
    private static final String DAMAGE_FORMAT = "###Damage: %d\n";
    private static final String STRENGTH_FORMAT = "###Strength: %d\n";
    private static final String AGILITY_FORMAT = "###Agility: %d\n";
    private static final String INTELLIGENCE_FORMAT = "###Intelligence: %d\n";
    private static final String NONE = "None";
    private static final String ITEMS_CONTAINED_FORMAT = "###Items: %s\n";

    private Map<String, Hero> heroes;

    public HeroDatabase() {
        this.heroes = new LinkedHashMap<>();
    }

    @Override
    public void addHero(Hero hero) {
        this.heroes.put(hero.getName(), hero);
    }

    @Override
    public void addItem(String name, Item item) {
        this.heroes.get(name).addItem(item);
    }

    @Override
    public void addRecipe(String name, Recipe recipe) {
        this.heroes.get(name).addRecipe(recipe);
    }

    @Override
    public String inspect(String name) {
        return this.heroes.get(name).toString();
    }

    @Override
    public String toString() {
        Comparator<Map.Entry<String, Hero>> orderByStatsAndThenByHpAndDamage = (a, b) -> {
            long sumAStats = a.getValue().getAgility() + a.getValue().getIntelligence() + a.getValue().getStrength();
            long sumBStats = b.getValue().getAgility() + b.getValue().getIntelligence() + b.getValue().getStrength();

            if (sumAStats == sumBStats) {
                return Long.compare((b.getValue().getDamage() + b.getValue().getHitPoints())
                        , (a.getValue().getDamage() + a.getValue().getHitPoints()));
            } else {
                return Long.compare(sumBStats, sumAStats);
            }
        };

        StringBuilder sb = new StringBuilder();
        final int[] counter = {1};
        heroes.entrySet().stream().sorted(orderByStatsAndThenByHpAndDamage).forEach(key -> {

            sb.append(String.format(HERO_CLASS_AND_NAME_FORMAT, counter[0]++
                    , key.getValue().getClass().getSimpleName(), key.getKey()));
            sb.append(String.format(HIT_POINTS_FORMAT, key.getValue().getHitPoints()));
            sb.append(String.format(DAMAGE_FORMAT, key.getValue().getDamage()));
            sb.append(String.format(STRENGTH_FORMAT, key.getValue().getStrength()));
            sb.append(String.format(AGILITY_FORMAT, key.getValue().getAgility()));
            sb.append(String.format(INTELLIGENCE_FORMAT, key.getValue().getIntelligence()));
            String toPrint = key.getValue().getItems().isEmpty() ? NONE :
                    String.join(", ", key.getValue().getItems()
                            .stream().map(Item::getName).collect(Collectors.toList()));
            sb.append(String.format(ITEMS_CONTAINED_FORMAT, toPrint));
        });

        return sb.toString().trim();
    }
}
