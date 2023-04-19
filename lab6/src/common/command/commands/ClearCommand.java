package command.commands;

import collection.CollectionManager;
import command.core.Command;
import data.LabWork;

import static io.OutputManager.print;
import static io.OutputManager.printErr;

public class ClearCommand implements Command {
    private final CollectionManager<LabWork> collectionManager;

    public ClearCommand(CollectionManager<LabWork> collectionManager){
        this.collectionManager = collectionManager;
    }

    public void run(String arg) {
        if (collectionManager.getSize() == 0)
            printErr("collection is empty");
        else{
            collectionManager.clear();
            print("collection is cleared");
        }
    }
}
