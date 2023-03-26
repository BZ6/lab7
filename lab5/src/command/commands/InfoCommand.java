package command.commands;

import collection.CollectionManager;
import command.core.Command;
import data.LabWork;

import static io.OutputManager.print;

public class InfoCommand implements Command {
    private final CollectionManager<LabWork> collectionManager;

    public InfoCommand(CollectionManager<LabWork> collectionManager){
        this.collectionManager = collectionManager;
    }
    public void run(String arg){
        print(collectionManager.getInfo());
    }
}
