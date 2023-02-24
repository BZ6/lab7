package command.commands;

import collection.CollectionManager;
import data.LabWork;
import exceptions.CommandException;
import file.ReaderWriter;

import static io.OutputManager.print;

public class LoadCommand {
    private final CollectionManager<LabWork> collectionManager;
    private final ReaderWriter fileManager;

    public LoadCommand(CollectionManager<LabWork> collectionManager, ReaderWriter fileManager){
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }

    public void load(String arg){
        if (!(arg == null ||arg.equals(""))) fileManager.setPath(arg);
        String data = fileManager.read();
        if(data.equals("")) throw new CommandException("cannot load, data not found");
        boolean success = collectionManager.deserializeCollection(data);
        if(success) print("collection successfully loaded");
    }
}
