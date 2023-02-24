package command.commands;

import collection.CollectionManager;
import data.LabWork;
import exceptions.InvalidDataException;
import io.InputManager;

public class AddIfMaxCommand {
    private final InputManager inputManager;
    private final CollectionManager<LabWork> collectionManager;

    public AddIfMaxCommand(InputManager inputManager, CollectionManager<LabWork> collectionManager) {
        this.inputManager = inputManager;
        this.collectionManager = collectionManager;
    }

    public void addIfMax() throws InvalidDataException {
        collectionManager.addIfMax(inputManager.readLabWork());
    }
}
