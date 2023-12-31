package command.commands;

import collection.CollectionManager;
import command.core.Command;
import data.LabWork;
import exceptions.EmptyCollectionException;
import exceptions.InvalidCommandArgumentException;
import exceptions.InvalidDataException;
import exceptions.MissedCommandArgumentException;
import io.InputManager;

public class UpdateCommand implements Command {
    private final InputManager inputManager;
    private final CollectionManager<LabWork> collectionManager;
    private static final Integer THE_FIRST_ID = 1;

    public UpdateCommand(InputManager inputManager, CollectionManager<LabWork> collectionManager) {
        this.inputManager = inputManager;
        this.collectionManager = collectionManager;
    }

    public void run(String arg) throws InvalidDataException {
        Integer id = THE_FIRST_ID;
        if (arg == null || arg.equals("")){
            throw new MissedCommandArgumentException();
        }
        try{
            id = Integer.parseInt(arg);
        } catch (NumberFormatException e){
            throw new InvalidCommandArgumentException("id must be integer");
        }
        if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
        if (!collectionManager.checkId(id)) throw new InvalidCommandArgumentException("no such id");

        collectionManager.updateById(id, inputManager.readLabWork());
    }
}
