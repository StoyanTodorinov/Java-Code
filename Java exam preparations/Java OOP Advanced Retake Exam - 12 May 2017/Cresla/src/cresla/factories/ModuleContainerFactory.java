package cresla.factories;

import cresla.entities.containers.ModuleContainer;
import cresla.interfaces.Container;

public final class ModuleContainerFactory {

    public static Container createContainer(int capacity) {
        return new ModuleContainer(capacity);
    }
}
