package command.commands;

import collection.CollectionManager;
import command.core.Command;
import data.LabWork;
import exceptions.EmptyCollectionException;
import exceptions.InvalidCommandArgumentException;
import exceptions.MissedCommandArgumentException;

public class RemoveByIdCommand implements Command {
    private final CollectionManager<LabWork> collectionManager;

    public RemoveByIdCommand(CollectionManager<LabWork> collectionManager){
        this.collectionManager = collectionManager;
    }

    public void run(String arg){
        int id;
        if (arg == null || arg.equals("")) throw new MissedCommandArgumentException();
        try{
            id = Integer.parseInt(arg);
        } catch (NumberFormatException e){
            throw new InvalidCommandArgumentException("id must be integer");
        }
        if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
        if (!collectionManager.checkId(id)) throw new InvalidCommandArgumentException("no such id");

        collectionManager.removeByID(id);}
}
