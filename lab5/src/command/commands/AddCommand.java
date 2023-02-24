package command.commands;

import collection.CollectionManager;
import data.LabWork;
import exceptions.InvalidDataException;
import io.InputManager;

public class AddCommand {
    private final InputManager inputManager;
    private final CollectionManager<LabWork> collectionManager;

    public AddCommand(InputManager inputManager, CollectionManager<LabWork> collectionManager) {
        this.inputManager = inputManager;
        this.collectionManager = collectionManager;
    }
    public void add() throws InvalidDataException {
        collectionManager.add(inputManager.readLabWork());
    }
}
