package command.commands;

import collection.CollectionManager;
import command.core.Command;
import data.LabWork;
import exceptions.EmptyCollectionException;

import static io.OutputManager.print;

public class ShowCommand implements Command {
    private final CollectionManager<LabWork> collectionManager;

    public ShowCommand(CollectionManager<LabWork> collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void run(String arg){
        if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
        print(collectionManager.serializeCollection());
    }
}
