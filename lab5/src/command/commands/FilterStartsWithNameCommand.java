package command.commands;

import collection.CollectionManager;
import command.core.Command;
import data.LabWork;
import exceptions.EmptyCollectionException;
import exceptions.MissedCommandArgumentException;

public class FilterStartsWithNameCommand implements Command {
    private final CollectionManager<LabWork> collectionManager;

    public FilterStartsWithNameCommand(CollectionManager<LabWork> collectionManager){
        this.collectionManager = collectionManager;
    }

    public void run(String arg){
        if (arg == null || arg.equals("")){
            throw new MissedCommandArgumentException();
        } else{
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
            collectionManager.printStartsWithName(arg);
        }
    }
}
