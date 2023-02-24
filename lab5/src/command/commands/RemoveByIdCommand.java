package command.commands;

import collection.CollectionManager;
import data.LabWork;
import exceptions.EmptyCollectionException;
import exceptions.InvalidCommandArgumentException;
import exceptions.MissedCommandArgumentException;

public class RemoveByIdCommand {
    private final CollectionManager<LabWork> collectionManager;
    private static final Integer THE_FIRST_ID = 1;

    public RemoveByIdCommand(CollectionManager<LabWork> collectionManager){
        this.collectionManager = collectionManager;
    }

    public void removeById(String arg){
        int id = THE_FIRST_ID;
        if (arg == null || arg.equals("")) throw new MissedCommandArgumentException();
        try{
            id = Integer.parseInt(arg);
        } catch (NumberFormatException e){
            throw new InvalidCommandArgumentException("id must be integer");
        }
        if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
        if (!collectionManager.checkID(id)) throw new InvalidCommandArgumentException("no such id");

        collectionManager.removeByID(id);}
}
