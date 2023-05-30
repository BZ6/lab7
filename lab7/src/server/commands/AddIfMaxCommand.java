package server.commands;

import common.command.core.CommandImpl;
import common.command.core.CommandType;
import common.exceptions.*;
import common.data.*;
import server.collection.CollectionManager;

public class AddIfMaxCommand extends CommandImpl {
    private CollectionManager<LabWork> collectionManager;
    public AddIfMaxCommand(CollectionManager<LabWork> cm){
        super("add_if_max", CommandType.NORMAL);
        collectionManager = cm;
    }

    @Override
    public String execute(){
        collectionManager.addIfMax(getLabWorkArg());
        return ("Added element: " + getLabWorkArg().toString());
    }
}
