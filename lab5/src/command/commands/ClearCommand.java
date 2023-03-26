package command.commands;

import collection.CollectionManager;
import command.core.Command;
import data.LabWork;

public class ClearCommand implements Command {
    private final CollectionManager<LabWork> collectionManager;

    public ClearCommand(CollectionManager<LabWork> collectionManager){
        this.collectionManager = collectionManager;
    }

    public void run(String arg) {
        collectionManager.clear();
    }
}
