package command.commands;

import collection.CollectionManager;
import command.core.Command;
import data.LabWork;
import exceptions.EmptyCollectionException;

public class MinByPersonalQualitiesMinimumCommand implements Command {
    private final CollectionManager<LabWork> collectionManager;

    public MinByPersonalQualitiesMinimumCommand(CollectionManager<LabWork> collectionManager){
        this.collectionManager = collectionManager;
    }
    public void run(String arg){
        if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
        collectionManager.minByPersonalQualitiesMinimum();
    }
}
