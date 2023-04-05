package command;

import collection.CollectionManager;
import command.commands.*;
import command.core.Commandable;
import command.core.Commands;
import data.LabWork;
import exceptions.CommandException;
import exceptions.InvalidDataException;
import exceptions.InvalidInputCharacterException;
import exceptions.NoSuchCommandException;
import file.ReaderWriter;
import io.ConsoleInputManager;
import io.FileInputManager;
import io.InputManager;
import utils.Bool;
import java.util.Stack;

import static io.OutputManager.print;
import static io.OutputManager.printErr;

public class CommandManager implements Commandable {
    private final Commands commands;
    private final CollectionManager<LabWork> collectionManager;
    private final ReaderWriter fileManager;
    private InputManager inputManager;
    private final Bool isRunning;
    private String currentScriptFileName;

    private static final Stack<String> callStack = new Stack<>();

    public void clearStack(){
        callStack.clear();
    }
    public CommandManager(CollectionManager<LabWork> cManager, InputManager iManager, ReaderWriter fManager){
        isRunning = new Bool();
        isRunning.setValue(false);
        this.inputManager = iManager;
        this.collectionManager = cManager;
        this.fileManager = fManager;
        currentScriptFileName = "";
        commands = new Commands();

        commands.addCommand("exit", new ExitCommand(isRunning));
        commands.addCommand("help", new HelpCommand());
        commands.addCommand("info", new InfoCommand(collectionManager));
        commands.addCommand("execute_script", new ExecuteScriptCommand(inputManager, collectionManager,
                fileManager, currentScriptFileName, callStack));
        commands.addCommand("add", new AddCommand(inputManager, collectionManager));
        commands.addCommand("show", new ShowCommand(collectionManager));
        commands.addCommand("update", new UpdateCommand(inputManager, collectionManager));
        commands.addCommand("remove_by_id", new RemoveByIdCommand(collectionManager));
        commands.addCommand("clear", new ClearCommand(collectionManager));
        commands.addCommand("save", new SaveCommand(collectionManager, fileManager));
        commands.addCommand("add_if_max", new AddIfMaxCommand(inputManager, collectionManager));
        commands.addCommand("remove_lower", new RemoveLowerCommand(inputManager, collectionManager));
        commands.addCommand("min_by_personal_qualities_minimum", new MinByPersonalQualitiesMinimumCommand(collectionManager));
        commands.addCommand("max_by_discipline", new MaxByDisciplineCommand(collectionManager));
        commands.addCommand("filter_starts_with_name", new FilterStartsWithNameCommand(collectionManager));
        commands.addCommand("history", new HistoryCommand(commands.getCommandHistory()));
        commands.addCommand("load", new LoadCommand(collectionManager, fileManager));
    }

    public void runCommand(String s, String arg){
        try{
            if (!commands.hasCommand(s)) throw new NoSuchCommandException();
            commands.get(s).run(arg);
        } catch(CommandException | InvalidDataException e){
            printErr(e.getMessage());
        }
    }
    public void runCommand(String s){
        runCommand(s,"default");
    }

    public void consoleMode(){
        inputManager = new ConsoleInputManager();
        isRunning.setValue(true);
        while (isRunning.getValue()){
            print("enter command (help to get command list): ");
            try{
                CommandWrapper pair = inputManager.readCommand();
                runCommand(pair.getCommand(), pair.getArg());
            }
            catch (InvalidInputCharacterException e){
                printErr(e.getMessage());
                isRunning.setValue(false);
                break;
            }
        }
    }
    public boolean fileMode(String path){
        currentScriptFileName = path;
        inputManager = new FileInputManager(path);
        isRunning.setValue(true);
        while (isRunning.getValue() && inputManager.getScanner().hasNextLine()){
            try{
                CommandWrapper pair = inputManager.readCommand();
                runCommand(pair.getCommand(), pair.getArg());
            }
            catch (InvalidInputCharacterException e){
                printErr(e.getMessage());
                return false;
            }
        }
        return true;
    }
}