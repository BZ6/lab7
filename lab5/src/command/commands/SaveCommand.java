package command.commands;

import collection.CollectionManager;
import command.core.Command;
import data.LabWork;
import exceptions.CommandException;
import file.ReaderWriter;
import io.InputManager;

import java.util.Stack;

import static io.OutputManager.print;

public class SaveCommand implements Command {
    private final CollectionManager<LabWork> collectionManager;
    private final ReaderWriter fileManager;

    public SaveCommand(CollectionManager<LabWork> collectionManager, ReaderWriter fileManager){
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }

    public void run(String arg){
        if (!(arg == null ||arg.equals(""))) fileManager.setPath(arg);
        if (collectionManager.getCollection().isEmpty()) print("collection is empty");
        if(!fileManager.write(collectionManager.serializeCollection())) throw new CommandException("cannot save collection");
    }
}
