package tests;

import cresla.entities.containers.ModuleContainer;
import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.Container;
import cresla.interfaces.EnergyModule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.Map;

public class ModuleContainerShould {
    private static final int MODULE_CONTAINER_CAPACITY = 5;
    private static final long LONG_MULTIPLIER = 2L;
    private static final int INT_MAX_VALUE = 2147483647;
    private static final int EXPECTED_MAP_SIZE = 5;

    private Container moduleContainer;

    @Before
    public void init() {
        this.moduleContainer = new ModuleContainer(MODULE_CONTAINER_CAPACITY);
    }

    @Test
    public void addEnergyModuleWithFullCapacity() throws NoSuchFieldException, IllegalAccessException {
        this.addEnergyModulesToContainer();

        EnergyModule mock = Mockito.mock(EnergyModule.class);
        Mockito.when(mock.getId()).thenReturn(6);
        this.moduleContainer.addEnergyModule(mock);

        Class<?> cl = ModuleContainer.class;
        Field energyModulesField = cl.getDeclaredField("energyModules");
        energyModulesField.setAccessible(true);
        Map<Integer, EnergyModule> map = (Map<Integer, EnergyModule>) energyModulesField.get(this.moduleContainer);

        Assert.assertEquals(EXPECTED_MAP_SIZE, map.size());
    }

    @Test
    public void addHeatAbsorbingModuleWithFullCapacity() throws NoSuchFieldException, IllegalAccessException {
        this.addAbsorbingModulesInContainer();

        AbsorbingModule mock5 = Mockito.mock(AbsorbingModule.class);
        Mockito.when(mock5.getId()).thenReturn(6);
        this.moduleContainer.addAbsorbingModule(mock5);

        Class<?> cl = ModuleContainer.class;
        Field absorbingModuleField = cl.getDeclaredField("absorbingModules");
        absorbingModuleField.setAccessible(true);
        Map<Integer, AbsorbingModule> map = (Map<Integer, AbsorbingModule>) absorbingModuleField.get(this.moduleContainer);
        Assert.assertEquals(EXPECTED_MAP_SIZE, map.size());
    }

    @Test
    public void addEnergyModulesAndHeatAbsorbingModulesAndOverloadCapacity() throws NoSuchFieldException, IllegalAccessException {
        this.addEnergyAndAbsorbingModulesInContainer();

        AbsorbingModule amock4 = Mockito.mock(AbsorbingModule.class);
        Mockito.when(amock4.getId()).thenReturn(6);
        this.moduleContainer.addAbsorbingModule(amock4);

        Class<?> cl = ModuleContainer.class;
        Field absorbingModuleField = cl.getDeclaredField("absorbingModules");
        absorbingModuleField.setAccessible(true);
        Map<Integer, AbsorbingModule> map = (Map<Integer, AbsorbingModule>) absorbingModuleField.get(this.moduleContainer);
        Assert.assertEquals(EXPECTED_MAP_SIZE, map.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionWhenNullIsPassedToAddEnergyModule() {
        this.moduleContainer.addEnergyModule(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionWhenNullIsPassedToAddAbsorbingModule() {
        this.moduleContainer.addAbsorbingModule(null);
    }

    @Test
    public void testOverloadOfIntegerWithGetTotalEnergy() {

        EnergyModule mock1 = Mockito.mock(EnergyModule.class);
        EnergyModule mock2 = Mockito.mock(EnergyModule.class);

        Mockito.when(mock1.getEnergyOutput()).thenReturn(INT_MAX_VALUE);
        Mockito.when(mock2.getEnergyOutput()).thenReturn(INT_MAX_VALUE);

        Mockito.when(mock1.getId()).thenReturn(1);
        Mockito.when(mock2.getId()).thenReturn(2);

        this.moduleContainer.addEnergyModule(mock1);
        this.moduleContainer.addEnergyModule(mock2);

        long expected = INT_MAX_VALUE * LONG_MULTIPLIER;

        Assert.assertEquals( expected, this.moduleContainer.getTotalEnergyOutput());
    }

    @Test
    public void testOverloadOfIntegerWithGetTotalAbsorbing() {
        AbsorbingModule mock1 = Mockito.mock(AbsorbingModule.class);
        AbsorbingModule mock2 = Mockito.mock(AbsorbingModule.class);

        Mockito.when(mock1.getHeatAbsorbing()).thenReturn(INT_MAX_VALUE);
        Mockito.when(mock2.getHeatAbsorbing()).thenReturn(INT_MAX_VALUE);

        Mockito.when(mock1.getId()).thenReturn(1);
        Mockito.when(mock2.getId()).thenReturn(2);

        this.moduleContainer.addAbsorbingModule(mock1);
        this.moduleContainer.addAbsorbingModule(mock2);

        long expected = INT_MAX_VALUE * LONG_MULTIPLIER;

        Assert.assertEquals( expected, this.moduleContainer.getTotalHeatAbsorbing());
    }


    private void addEnergyModulesToContainer() {
        EnergyModule mock = Mockito.mock(EnergyModule.class);
        EnergyModule mock1 = Mockito.mock(EnergyModule.class);
        EnergyModule mock2 = Mockito.mock(EnergyModule.class);
        EnergyModule mock3 = Mockito.mock(EnergyModule.class);
        EnergyModule mock4 = Mockito.mock(EnergyModule.class);

        Mockito.when(mock.getId()).thenReturn(1);
        Mockito.when(mock1.getId()).thenReturn(2);
        Mockito.when(mock2.getId()).thenReturn(3);
        Mockito.when(mock3.getId()).thenReturn(4);
        Mockito.when(mock4.getId()).thenReturn(5);

        this.moduleContainer.addEnergyModule(mock);
        this.moduleContainer.addEnergyModule(mock1);
        this.moduleContainer.addEnergyModule(mock2);
        this.moduleContainer.addEnergyModule(mock3);
        this.moduleContainer.addEnergyModule(mock4);
    }

    private void addAbsorbingModulesInContainer() {
        AbsorbingModule mock = Mockito.mock(AbsorbingModule.class);
        AbsorbingModule mock1 = Mockito.mock(AbsorbingModule.class);
        AbsorbingModule mock2 = Mockito.mock(AbsorbingModule.class);
        AbsorbingModule mock3 = Mockito.mock(AbsorbingModule.class);
        AbsorbingModule mock4 = Mockito.mock(AbsorbingModule.class);

        Mockito.when(mock.getId()).thenReturn(1);
        Mockito.when(mock1.getId()).thenReturn(2);
        Mockito.when(mock2.getId()).thenReturn(3);
        Mockito.when(mock3.getId()).thenReturn(4);
        Mockito.when(mock4.getId()).thenReturn(5);

        this.moduleContainer.addAbsorbingModule(mock);
        this.moduleContainer.addAbsorbingModule(mock1);
        this.moduleContainer.addAbsorbingModule(mock2);
        this.moduleContainer.addAbsorbingModule(mock3);
        this.moduleContainer.addAbsorbingModule(mock4);
    }

    private void addEnergyAndAbsorbingModulesInContainer() {
        EnergyModule mock = Mockito.mock(EnergyModule.class);
        AbsorbingModule amock = Mockito.mock(AbsorbingModule.class);
        AbsorbingModule amock1 = Mockito.mock(AbsorbingModule.class);
        AbsorbingModule amock2 = Mockito.mock(AbsorbingModule.class);
        AbsorbingModule amock3 = Mockito.mock(AbsorbingModule.class);

        Mockito.when(mock.getId()).thenReturn(1);
        Mockito.when(amock.getId()).thenReturn(2);
        Mockito.when(amock1.getId()).thenReturn(3);
        Mockito.when(amock2.getId()).thenReturn(4);
        Mockito.when(amock3.getId()).thenReturn(5);

        this.moduleContainer.addEnergyModule(mock);
        this.moduleContainer.addAbsorbingModule(amock);
        this.moduleContainer.addAbsorbingModule(amock1);
        this.moduleContainer.addAbsorbingModule(amock2);
        this.moduleContainer.addAbsorbingModule(amock3);
    }
}
