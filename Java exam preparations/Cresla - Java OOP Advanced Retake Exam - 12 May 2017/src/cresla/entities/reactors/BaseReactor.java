package cresla.entities.reactors;

import cresla.entities.containers.ModuleContainer;
import cresla.factories.ModuleContainerFactory;
import cresla.interfaces.*;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.Deque;

public abstract class BaseReactor implements Reactor {
    private static final String REACTOR_NAME_AND_ID_FORMAT = "%s - %d\n";
    private static final String ENERGY_OUTPUT_FORMAT = "Energy Output: %d\n";
    private static final String HEAT_ABSORBING_FORMAT = "Heat Absorbing: %d\n";
    private static final String MODULES_FORMAT = "Modules: %d\n";

    private int id;
    private Container moduleContainer;

    protected BaseReactor(int id, int capacity) {
        this.id = id;
        this.moduleContainer = ModuleContainerFactory.createContainer(capacity);
    }

    @Override
    public long getTotalEnergyOutput() {
        return this.moduleContainer.getTotalEnergyOutput();
    }

    @Override
    public long getTotalHeatAbsorbing() {
        return this.moduleContainer.getTotalHeatAbsorbing();
    }

    @Override
    public int getModuleCount() {
        return this.calculateModuleCount();
    }

    @Override
    public void addEnergyModule(EnergyModule energyModule) {
        this.moduleContainer.addEnergyModule(energyModule);
    }

    @Override
    public void addAbsorbingModule(AbsorbingModule absorbingModule) {
        this.moduleContainer.addAbsorbingModule(absorbingModule);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @SuppressWarnings("unchecked")
    private int calculateModuleCount() {
        Class<?> cl = ModuleContainer.class;
        Field modulesField;
        try {
            modulesField = cl.getDeclaredField("modulesByInput");
        } catch (NoSuchFieldException e) {
            return 0;
        }
        modulesField.setAccessible(true);
        Deque<Module> deque;
        try {
            deque = (ArrayDeque<Module>) modulesField.get(this.moduleContainer);
        } catch (IllegalAccessException e) {
            return 0;
        }
        return deque.size();
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(String.format(REACTOR_NAME_AND_ID_FORMAT, this.getClass().getSimpleName(), this.id));
        sb.append(String.format(ENERGY_OUTPUT_FORMAT, this.getTotalEnergyOutput()));
        sb.append(String.format(HEAT_ABSORBING_FORMAT, this.getTotalHeatAbsorbing()));
        sb.append(String.format(MODULES_FORMAT, this.getModuleCount()));

        return sb.toString();
    }
}
