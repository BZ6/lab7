package command.commands;

import collection.CollectionManager;
import data.LabWork;

public class InfoCommand {
    private final CollectionManager<LabWork> collectionManager;

    public InfoCommand(CollectionManager<LabWork> collectionManager){
        this.collectionManager = collectionManager;
    }
    public String getInfo(){
        return collectionManager.getInfo();
    }
}
