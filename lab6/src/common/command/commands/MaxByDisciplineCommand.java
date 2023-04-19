package command.commands;

import collection.CollectionManager;
import command.core.Command;
import data.LabWork;
import exceptions.EmptyCollectionException;

public class MaxByDisciplineCommand implements Command {
    private final CollectionManager<LabWork> collectionManager;

    public MaxByDisciplineCommand(CollectionManager<LabWork> collectionManager){
        this.collectionManager = collectionManager;
    }
    public void run(String arg){
        if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
        collectionManager.maxByDiscipline();
    }
}
