package command.commands;

import collection.CollectionManager;
import data.LabWork;

public class MinByPersonalQualitiesMinimumCommand {
    private final CollectionManager<LabWork> collectionManager;

    public MinByPersonalQualitiesMinimumCommand(CollectionManager<LabWork> collectionManager){
        this.collectionManager = collectionManager;
    }
    public void minByPersonalQualitiesMinimum(){
        collectionManager.minByPersonalQualitiesMinimum();;
    }
}
