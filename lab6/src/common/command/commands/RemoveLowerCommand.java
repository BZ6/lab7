package command.commands;

import collection.CollectionManager;
import command.core.Command;
import data.LabWork;
import exceptions.EmptyCollectionException;
import exceptions.InvalidDataException;
import io.InputManager;

public class RemoveLowerCommand implements Command {
    private final InputManager inputManager;
    private final CollectionManager<LabWork> collectionManager;

    public RemoveLowerCommand(InputManager inputManager, CollectionManager<LabWork> collectionManager) {
        this.inputManager = inputManager;
        this.collectionManager = collectionManager;
    }

    public void run(String arg) throws InvalidDataException {
        if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
        collectionManager.removeLower(inputManager.readLabWork());
    }
}
