package command.commands;

import collection.CollectionManager;
import command.core.Command;
import data.LabWork;

public class MinByPersonalQualitiesMinimumCommand implements Command {
    private final CollectionManager<LabWork> collectionManager;

    public MinByPersonalQualitiesMinimumCommand(CollectionManager<LabWork> collectionManager){
        this.collectionManager = collectionManager;
    }
    public void run(String arg){
        collectionManager.minByPersonalQualitiesMinimum();
    }
}
