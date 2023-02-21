package commands;

import static io.OutputManager.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import collection.CollectionManager;
import data.*;
import exceptions.*;
import file.ReaderWriter;
import io.*;
public class CommandManager implements Commandable{
    private Map<String,Command> map;
    private CollectionManager<LabWork> collectionManager;
    private InputManager inputManager;
    private ReaderWriter fileManager;
    private boolean isRunning;
    private String currentScriptFileName;

    private static Stack<String>
            callStack = new Stack<>(),
            history = new Stack<>();

    public void clearStack(){
        callStack.clear();
    }
    public CommandManager(CollectionManager<LabWork> cManager, InputManager iManager, ReaderWriter fManager){

        isRunning = false;
        this.inputManager = iManager;
        this.collectionManager = cManager;
        this.fileManager = fManager;

        currentScriptFileName = "";
        map = new HashMap<String,Command>();
        addCommand("info", (a)->print(collectionManager.getInfo()));
        addCommand("help", (a)->print(getHelp()));
        addCommand("show", (a)->{
            if (collectionManager.getCollection().isEmpty()) print("collection is empty");
            else print(collectionManager.serializeCollection());
        });
        addCommand("add", (a)->{
            collectionManager.add(inputManager.readLabWork());
        });
        addCommand("update", (arg)->{
            int id = 0;
            if (arg == null || arg.equals("")){
                throw new MissedCommandArgumentException();
            }
            try{
                id = Integer.parseInt(arg);
            } catch (NumberFormatException e){
                throw new InvalidCommandArgumentException("id must be integer");
            }
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
            if (!collectionManager.checkID(id)) throw new InvalidCommandArgumentException("no such id");

            collectionManager.updateByID(id, inputManager.readLabWork());

        });
        addCommand("remove_by_id", (arg)->{
            int id = 0;
            if (arg == null || arg.equals("")) throw new MissedCommandArgumentException();
            try{
                id = Integer.parseInt(arg);
            } catch (NumberFormatException e){
                throw new InvalidCommandArgumentException("id must be integer");
            }
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
            if (!collectionManager.checkID(id)) throw new InvalidCommandArgumentException("no such id");

            collectionManager.removeByID(id);

        });
        addCommand("clear", (a)->{
            collectionManager.clear();
        });

        addCommand("save", (arg)->{
            if (!(arg == null ||arg.equals(""))) fileManager.setPath(arg);
            if (collectionManager.getCollection().isEmpty()) print("collection is empty");
            if(!fileManager.write(collectionManager.serializeCollection())) throw new CommandException("cannot save collection");

        });
        addCommand("execute_script",(arg)->{
            if (arg == null || arg.equals("")) throw new MissedCommandArgumentException();

            if (callStack.contains(currentScriptFileName)) throw new RecursiveScriptExecuteException();

            callStack.push(currentScriptFileName);
            CommandManager process = new CommandManager(collectionManager, inputManager, fileManager);
            process.fileMode(arg);
            callStack.pop();
            print("successfully executed script " + arg);

        });
        addCommand("exit", (a)->isRunning=false);
        addCommand("add_if_max", (a)->collectionManager.addIfMax(inputManager.readLabWork()));

        addCommand("remove_lower", (a)->collectionManager.removeLower(inputManager.readLabWork()));
        addCommand("history", (a)->{
            if (history.isEmpty()) {
                print("history is empty");
            } else if (history.size() < 10) {
                print("history have not 10 commands in history");
            } else{
                for (int i=0; i<10; i++)
                    print(history.get(history.size()-i-1));
            }
        });
        addCommand("min_by_personal_qualities_minimum", (a)->collectionManager.minByPersonalQualitiesMinimum());
        addCommand("max_by_discipline", (a)->collectionManager.maxByDiscipline());

        addCommand("filter_starts_with_name", (arg)->{
            if (arg == null || arg.equals("")){
                throw new MissedCommandArgumentException();
            } else{
                if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
                collectionManager.printStartsWithName(arg);
            }
        });
        addCommand("load", (arg)->{
            if (!(arg == null ||arg.equals(""))) fileManager.setPath(arg);
            String data = fileManager.read();
            if(data.equals("")) throw new CommandException("cannot load, data not found");
            boolean success = collectionManager.deserializeCollection(data);
            if(success) print("collection successfully loaded");
        });
    }

    public void addCommand(String key, Command c){
        map.put(key, c);
    }

    public void runCommand(String s, String arg){
        try{
            if (! hasCommand(s)) throw new NoSuchCommandException();
            map.get(s).run(arg);
            history.push(s);
        }
        catch(CommandException|InvalidDataException e){
            printErr(e.getMessage());
        }
    }
    public void runCommand(String s){
        runCommand(s,null);
    }
    public boolean hasCommand(String s){
        return map.containsKey(s);
    }
    public void consoleMode(){
        inputManager = new ConsoleInputManager();
        isRunning = true;
        while(isRunning){
            System.out.print("enter command (help to get command list): ");
            CommandWrapper pair = inputManager.readCommand();
            runCommand(pair.getCommand(), pair.getArg());
        }
    }
    public void fileMode(String path){
        currentScriptFileName = path;
        isRunning = true;
        inputManager = new FileInputManager(path);
        isRunning = true;
        while(isRunning && inputManager.getScanner().hasNextLine()){
            CommandWrapper pair = inputManager.readCommand();
            runCommand(pair.getCommand(), pair.getArg());
        }
    }

    public static String getHelp(){
        return "\r\n" +
                "help : show help for available commands\r\n\r\n" +

                "info : Write to standard output information about the collection (type,\r\n" +
                "initialization date, number of elements, etc.)\r\n\r\n" +

                "show : print to standard output all elements of the collection in\r\n" +
                "string representation\r\n\r\n" +

                "add {element} : add a new element to the collection\r\n\r\n" +

                "update id {element} : update the value of the collection element whose id\r\n" +
                "equal to given\r\n\r\n" +

                "remove_by_id id : remove an element from the collection by its id\r\n\r\n" +

                "clear : clear the collection\r\n\r\n" +

                "save (file_name - optional) : save the collection to a file\r\n\r\n" +

                "load (file_name - optional): load collection from file\r\n\r\n" +

                "execute_script file_name : read and execute script from specified file.\r\n" +
                "The script contains commands in the same form in which they are entered\r\n" +
                "user is interactive.\r\n\r\n" +

                "exit : exit the program (without saving to a file)\r\n\r\n" +

                "add_if_max {element} : add a new element to the collection if its\r\n\r\n" +
                "value is greater than the value of the largest element of this collection\r\n\r\n" +

                "remove_lower {element} : remove from the collection all elements smaller\r\n" +
                "than the given one\r\n\r\n" +

                "history : print the last 10 commands (without their arguments)\r\n\r\n" +

                "min_by_personal_qualities_minimum : print any object from the collection whose\r\n" +
                "personalQualitiesMinimum field value is the minimum\r\n\r\n" +

                "max_by_discipline : print any object from the collection whose discipline field\r\n" +
                "value is the maximum\r\n\r\n" +

                "filter_starts_with_name name : output elements, value of field name\r\n" +
                "which starts with the given substring\r\n\r\n";
    }
}