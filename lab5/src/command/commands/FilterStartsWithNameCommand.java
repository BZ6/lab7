package command.commands;

import collection.CollectionManager;
import data.LabWork;
import exceptions.EmptyCollectionException;
import exceptions.MissedCommandArgumentException;

public class FilterStartsWithNameCommand {
    private final CollectionManager<LabWork> collectionManager;

    public FilterStartsWithNameCommand(CollectionManager<LabWork> collectionManager){
        this.collectionManager = collectionManager;
    }

    public void filterStartsWithNameCommand(String arg){
        if (arg == null || arg.equals("")){
            throw new MissedCommandArgumentException();
        } else{
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
            collectionManager.printStartsWithName(arg);
        }
    }
}
