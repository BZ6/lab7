package server.commands;

import common.auth.User;
import common.command.core.CommandImpl;
import common.command.core.CommandType;
import common.exceptions.*;
import common.data.*;
import server.collection.CollectionManager;
import server.exceptions.CheckIdException;

import static common.utils.Parser.*;
public class UpdateCommand extends CommandImpl {
    static private LabWork previous = null;
    private CollectionManager<LabWork> collectionManager;
    public UpdateCommand(CollectionManager<LabWork> cm){
        super("update", CommandType.NORMAL);
        collectionManager = cm;
    }

    @Override
    public String execute() throws InvalidDataException, AuthException {
        User user = getArgument().getUser();
        if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
        if (!hasStringArg()) throw new MissedCommandArgumentException();
        Integer id = parseId(getStringArg());
        if (!collectionManager.checkId(id)) throw new InvalidCommandArgumentException("no such id");
        String labWorkCreatorLogin = user.getLogin();
        String owner = collectionManager.getById(id).getUserLogin();

        if (labWorkCreatorLogin == null || !labWorkCreatorLogin.equals(owner)) {
            throw new AuthException("you dont have permission, element was created by " + owner);
        }

        if (!hasLabWorkArg()) throw new CheckIdException();

        boolean success = collectionManager.updateById(id, getLabWorkArg());
        if (success) return "element #" + Integer.toString(id) + " updated";
        else throw new CommandException("cannot update");
    }
}
