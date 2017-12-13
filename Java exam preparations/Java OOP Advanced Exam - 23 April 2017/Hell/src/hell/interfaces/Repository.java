package hell.interfaces;

public interface Repository {
    void addHero(Hero hero);

    void addItem(String name, Item item);

    void addRecipe(String name, Recipe recipe);

    String inspect(String name);
}
