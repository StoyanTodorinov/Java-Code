package cresla.entities.core.commands;

import cresla.database.Repository;
import cresla.factories.ModuleFactory;
import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.EnergyModule;

import java.util.List;

public class ModuleCommand extends BaseCommand {
    private static final String ADD_MODULE_FORMAT = "Added %s - %d to Reactor - %d\n";

    @Override
    public String execute() {

        Repository repository = super.getRepository();
        List<String> tokens = super.getData();

        EnergyModule energyModule = ModuleFactory.createEnergyModule(tokens.get(2)
                , Integer.parseInt(tokens.get(0)), Integer.parseInt(tokens.get(3)));

        AbsorbingModule absorbingModule = ModuleFactory.createAbsorbingModule(tokens.get(2)
                , Integer.parseInt(tokens.get(0)), Integer.parseInt(tokens.get(3)));

        if (energyModule != null) {
            repository.addEnergyModule(Integer.parseInt(tokens.get(1)), energyModule);
            return String.format(ADD_MODULE_FORMAT
                    , energyModule.getClass().getSimpleName(), energyModule.getId(), Integer.parseInt(tokens.get(1)));
        } else {
            repository.addAbsorbingModule(Integer.parseInt(tokens.get(1)), absorbingModule);
            return String.format(ADD_MODULE_FORMAT
                    , absorbingModule.getClass().getSimpleName(), absorbingModule.getId(), Integer.parseInt(tokens.get(1)));
        }
    }
}
