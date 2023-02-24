package command.commands;

import collection.CollectionManager;
import data.LabWork;

import static io.OutputManager.print;

public class ShowCommand {
    private final CollectionManager<LabWork> collectionManager;

    public ShowCommand(CollectionManager<LabWork> collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void show(){
        if (collectionManager.getCollection().isEmpty()) print("collection is empty");
        else print(collectionManager.serializeCollection());
    }
}
