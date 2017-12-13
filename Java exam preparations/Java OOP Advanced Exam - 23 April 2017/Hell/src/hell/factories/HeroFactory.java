package hell.factories;

import hell.interfaces.Hero;
import hell.entities.heroes.Assassin;
import hell.entities.heroes.Barbarian;
import hell.entities.heroes.Wizard;

public final class HeroFactory {

    private static final String WIZARD = "Wizard";
    private static final String BARBARIAN = "Barbarian";
    private static final String ASSASSIN = "Assassin";

    public static Hero createHero(String type, String name, String heroType) {
        switch (type) {
            case BARBARIAN:
                return new Barbarian(name, heroType);
            case ASSASSIN:
                return new Assassin(name, heroType);
            case WIZARD:
                return new Wizard(name, heroType);
            default:
                return null;
        }
    }
}
