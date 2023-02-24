package command.commands;

import collection.CollectionManager;
import data.LabWork;

public class MaxByDisciplineCommand {
    private final CollectionManager<LabWork> collectionManager;

    public MaxByDisciplineCommand(CollectionManager<LabWork> collectionManager){
        this.collectionManager = collectionManager;
    }
    public void maxByDiscipline(){
        collectionManager.maxByDiscipline();
    }
}
