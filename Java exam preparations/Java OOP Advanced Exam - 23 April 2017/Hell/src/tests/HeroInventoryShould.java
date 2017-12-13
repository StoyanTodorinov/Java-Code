package tests;

import hell.entities.miscellaneous.HeroInventory;
import hell.interfaces.Inventory;
import hell.interfaces.Item;
import hell.interfaces.Recipe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.*;

public class HeroInventoryShould {
    private static final String ADDING_COMMON_ITEMS_DOESN_T_WORK_PROPERLY_MESSAGE = "Adding common items doesn't work properly";
    private static final String INCORRECT_TOTAL_STRENGTH_RESULT_MESSAGE = "Incorrect total strength result";
    private static final String INCORRECT_TOTAL_AGILITY_RESULT_MESSAGE = "Incorrect total agility result";
    private static final String INCORRECT_TOTAL_INTELLIGENCE_RESULT_MESSAGE = "Incorrect total intelligence result";
    private static final String INCORRECT_TOTAL_HIT_POINTS_RESULT_MESSAGE = "Incorrect total hitPoints result";
    private static final String INCORRECT_TOTAL_DAMAGE_RESULT_MESSAGE = "Incorrect total damage result";
    private static final String COMBINE_RECIPE_FAIL_MESSAGE = "Combine recipe fail";
    private static final String BATA = "Bata";
    private static final String RAPIER = "Rapier";
    private static final String SKULL = "Skull";
    private static final String ORB = "Orb";
    private static final int EXPECTED_RETURN_VALUE = 13;
    private static final int EXPECTED_ITEM_MAP_SIZE = 2;
    private static final int EXPECTED_ITEM_MAP_SIZE_WHEN_RECIPE_IS_ADDED = 1;
    private static final int EXPECTED_MAP_SIZE_WHEN_TESTING_ADDING_COMMON_ITEM = 4;

    private Map<String, Item> commonItems;
    private Inventory inventory;

    @Before
    public void fillMapAndMockItems() throws NoSuchFieldException, IllegalAccessException {
        this.commonItems = new LinkedHashMap<>();
        this.inventory = new HeroInventory();
        this.mockAndPutIntoMap();
    }

    @Test
    public void returnCorrectTotalStrengthBonus() {
        Assert.assertEquals(INCORRECT_TOTAL_STRENGTH_RESULT_MESSAGE, EXPECTED_RETURN_VALUE, this.inventory.getTotalStrengthBonus());
    }

    @Test
    public void returnCorrectTotalAgilityBonus() {
        Assert.assertEquals(INCORRECT_TOTAL_AGILITY_RESULT_MESSAGE, EXPECTED_RETURN_VALUE, this.inventory.getTotalAgilityBonus());
    }

    @Test
    public void returnCorrectTotalIntelligenceBonus() {
        Assert.assertEquals(INCORRECT_TOTAL_INTELLIGENCE_RESULT_MESSAGE, EXPECTED_RETURN_VALUE
                , this.inventory.getTotalIntelligenceBonus());
    }

    @Test
    public void returnCorrectTotalHitPointsBonus() {
        Assert.assertEquals(INCORRECT_TOTAL_HIT_POINTS_RESULT_MESSAGE, EXPECTED_RETURN_VALUE
                , this.inventory.getTotalHitPointsBonus());
    }

    @Test
    public void returnCorrectTotalDamageBonus() {
        Assert.assertEquals(INCORRECT_TOTAL_DAMAGE_RESULT_MESSAGE, EXPECTED_RETURN_VALUE, this.inventory.getTotalDamageBonus());
    }

    @Test
    public void addCommonItemShouldWorkProperly() throws NoSuchFieldException, IllegalAccessException {
        Item mock = Mockito.mock(Item.class);
        Mockito.when(mock.getName()).thenReturn(BATA);
        this.inventory.addCommonItem(mock);

        int actualSize = this.getItemCollectionSizeUsingReflection();

        Assert.assertEquals(ADDING_COMMON_ITEMS_DOESN_T_WORK_PROPERLY_MESSAGE
                , EXPECTED_MAP_SIZE_WHEN_TESTING_ADDING_COMMON_ITEM, actualSize);
    }

    @Test
    public void addCommonItemAndCompleteRecipe() throws NoSuchFieldException, IllegalAccessException {
        Recipe recipe = Mockito.mock(Recipe.class);
        List<String> requiredItems = new ArrayList<>();
        Collections.addAll(requiredItems, BATA, ORB, RAPIER);
        Mockito.when(recipe.getRequiredItems()).thenReturn(requiredItems);
        this.inventory.addRecipeItem(recipe);

        Item item = Mockito.mock(Item.class);
        Mockito.when(item.getName()).thenReturn(BATA);
        this.inventory.addCommonItem(item);

        int actualSize = this.getItemCollectionSizeUsingReflection();

        Assert.assertEquals(COMBINE_RECIPE_FAIL_MESSAGE, EXPECTED_ITEM_MAP_SIZE, actualSize);
    }

    @Test
    public void addRecipeShouldCombineItemsIntoOne() throws NoSuchFieldException, IllegalAccessException {
        Recipe recipe = Mockito.mock(Recipe.class);
        List<String> requiredItems = new ArrayList<>();
        Collections.addAll(requiredItems, SKULL, ORB, RAPIER);
        Mockito.when(recipe.getRequiredItems()).thenReturn(requiredItems);
        this.inventory.addRecipeItem(recipe);

        int actualSize = this.getItemCollectionSizeUsingReflection();

        Assert.assertEquals(COMBINE_RECIPE_FAIL_MESSAGE, EXPECTED_ITEM_MAP_SIZE_WHEN_RECIPE_IS_ADDED, actualSize);
    }

    private int getItemCollectionSizeUsingReflection() throws NoSuchFieldException, IllegalAccessException {
        Class<?> cl = HeroInventory.class;
        Field field = cl.getDeclaredField("commonItems");
        field.setAccessible(true);
        Map<String, Item> obj = (Map<String, Item>) field.get(this.inventory);
        return obj.size();
    }

    private void mockAndPutIntoMap() throws NoSuchFieldException, IllegalAccessException {
        Item mock1 = Mockito.mock(Item.class);
        Mockito.when(mock1.getName()).thenReturn(ORB);
        Mockito.when(mock1.getStrengthBonus()).thenReturn(1);
        Mockito.when(mock1.getAgilityBonus()).thenReturn(1);
        Mockito.when(mock1.getIntelligenceBonus()).thenReturn(1);
        Mockito.when(mock1.getHitPointsBonus()).thenReturn(1);
        Mockito.when(mock1.getDamageBonus()).thenReturn(1);

        Item mock2 = Mockito.mock(Item.class);
        Mockito.when(mock2.getName()).thenReturn(SKULL);
        Mockito.when(mock2.getStrengthBonus()).thenReturn(2);
        Mockito.when(mock2.getAgilityBonus()).thenReturn(2);
        Mockito.when(mock2.getIntelligenceBonus()).thenReturn(2);
        Mockito.when(mock2.getHitPointsBonus()).thenReturn(2);
        Mockito.when(mock2.getDamageBonus()).thenReturn(2);

        Item mock3 = Mockito.mock(Item.class);
        Mockito.when(mock3.getName()).thenReturn(RAPIER);
        Mockito.when(mock3.getStrengthBonus()).thenReturn(10);
        Mockito.when(mock3.getAgilityBonus()).thenReturn(10);
        Mockito.when(mock3.getIntelligenceBonus()).thenReturn(10);
        Mockito.when(mock3.getHitPointsBonus()).thenReturn(10);
        Mockito.when(mock3.getDamageBonus()).thenReturn(10);

        this.commonItems.put(mock1.getName(), mock1);
        this.commonItems.put(mock2.getName(), mock2);
        this.commonItems.put(mock3.getName(), mock3);

        Class<?> cl = HeroInventory.class;
        Field field = cl.getDeclaredField("commonItems");
        field.setAccessible(true);
        field.set(this.inventory, this.commonItems);
    }
}
