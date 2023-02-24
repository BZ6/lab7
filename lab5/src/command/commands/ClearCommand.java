package command.commands;

import collection.CollectionManager;
import data.LabWork;

public class ClearCommand {
    private final CollectionManager<LabWork> collectionManager;

    public ClearCommand(CollectionManager<LabWork> collectionManager){
        this.collectionManager = collectionManager;
    }

    public void clear(){
        collectionManager.clear();
    }
}
