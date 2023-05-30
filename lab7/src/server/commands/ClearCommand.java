package server.commands;

import common.auth.User;
import common.command.core.CommandImpl;
import common.command.core.CommandType;
import common.data.LabWork;
import common.exceptions.EmptyCollectionException;
import common.exceptions.InvalidDataException;
import server.collection.CollectionManager;
import server.database.LabWorkDatabaseManager;

public class ClearCommand extends CommandImpl {
    private final LabWorkDatabaseManager collectionManager;

    public ClearCommand(CollectionManager<LabWork> cm) {
        super("clear", CommandType.NORMAL);
        collectionManager = (LabWorkDatabaseManager) cm;
    }

    @Override
    public String execute() throws InvalidDataException {
        if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
        User user = getArgument().getUser();
        collectionManager.clear(user);
        return "collection cleared";
    }

}
