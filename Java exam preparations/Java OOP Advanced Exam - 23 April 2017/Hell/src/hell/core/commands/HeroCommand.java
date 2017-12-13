package hell.core.commands;

import hell.interfaces.Repository;
import hell.factories.HeroFactory;
import hell.interfaces.Hero;

import java.util.List;

public class HeroCommand extends BaseCommand {

    private static final String CREATED_HERO_FORMAT = "Created %s - %s";

    @Override
    public String execute() {
        List<String> tokens = super.getData();
        Repository repository = super.getRepository();
        Hero hero = HeroFactory.createHero(tokens.get(2), tokens.get(1), tokens.get(2));
        repository.addHero(hero);
        return String.format(CREATED_HERO_FORMAT, tokens.get(2), tokens.get(1));
    }
}
