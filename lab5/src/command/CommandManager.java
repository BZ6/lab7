package command;

import collection.CollectionManager;
import command.commands.*;
import command.core.Commandable;
import command.core.Commands;
import data.LabWork;
import exceptions.CommandException;
import exceptions.InvalidDataException;
import exceptions.NoSuchCommandException;
import file.ReaderWriter;
import io.ConsoleInputManager;
import io.FileInputManager;
import io.InputManager;

import java.util.Stack;

import static io.OutputManager.print;
import static io.OutputManager.printErr;
public class CommandManager implements Commandable {
    private final Commands commands;
    private final CollectionManager<LabWork> collectionManager;
    private final ReaderWriter fileManager;
    private InputManager inputManager;
    private boolean isRunning;
    private String currentScriptFileName;

    private static final Stack<String> callStack = new Stack<>();

    public void clearStack(){
        callStack.clear();
    }
    public CommandManager(CollectionManager<LabWork> cManager, InputManager iManager, ReaderWriter fManager){
        isRunning = false;
        this.inputManager = iManager;
        this.collectionManager = cManager;
        this.fileManager = fManager;
        currentScriptFileName = "";
        commands = new Commands();

        commands.addCommand("help", (a)->print(HelpCommand.
                            getHelp()));
        commands.addCommand("execute_script", (arg)->new ExecuteScriptCommand(inputManager, collectionManager,
                            fileManager, currentScriptFileName, callStack).
                            ExecuteScript(arg));
        commands.addCommand("info", (a)->print(new InfoCommand(collectionManager).
                            getInfo()));
        commands.addCommand("add", (a)->new AddCommand(inputManager, collectionManager).
                            add());
        commands.addCommand("show", (a)->new ShowCommand(collectionManager).
                            show());
        commands.addCommand("update", (arg)->new UpdateCommand(inputManager, collectionManager).
                            update(arg));
        commands.addCommand("remove_by_id", (arg)->new RemoveByIdCommand(collectionManager).
                            removeById(arg));
        commands.addCommand("clear", (a)->new ClearCommand(collectionManager).
                            clear());
        commands.addCommand("save", (arg)->new SaveCommand(collectionManager, fileManager).
                            save(arg));
        commands.addCommand("add_if_max", (a)->new AddIfMaxCommand(inputManager, collectionManager).
                            addIfMax());
        commands.addCommand("remove_lower", (a)->new RemoveLowerCommand(inputManager, collectionManager).
                            removeLower());
        commands.addCommand("min_by_personal_qualities_minimum", (a)->new MinByPersonalQualitiesMinimumCommand(collectionManager).
                            minByPersonalQualitiesMinimum());
        commands.addCommand("max_by_discipline", (a)->new MaxByDisciplineCommand(collectionManager).
                            maxByDiscipline());
        commands.addCommand("filter_starts_with_name", (arg)->new FilterStartsWithNameCommand(collectionManager).
                            filterStartsWithNameCommand(arg));
        commands.addCommand("history", (a)->new HistoryCommand(commands.getCommandHistory()).
                            history());
        commands.addCommand("load", (arg)->new LoadCommand(collectionManager, fileManager).
                            load(arg));
        commands.addCommand("exit", (a)->isRunning=ExitCommand.
                            exit());
    }

    public void runCommand(String s, String arg){
        try{
            if (!commands.hasCommand(s)) throw new NoSuchCommandException();
            commands.get(s).run(arg);
        }
        catch(CommandException|InvalidDataException e){
            printErr(e.getMessage());
        }
    }
    public void runCommand(String s){
        runCommand(s,"default");
    }

    public void consoleMode(){
        inputManager = new ConsoleInputManager();
        isRunning = true;
        while (isRunning){
            System.out.print("enter command (help to get command list): ");
            CommandWrapper pair = inputManager.readCommand();
            runCommand(pair.getCommand(), pair.getArg());
        }
    }
    public void fileMode(String path){
        currentScriptFileName = path;
        inputManager = new FileInputManager(path);
        isRunning = true;
        while (isRunning && inputManager.getScanner().hasNextLine()){
            CommandWrapper pair = inputManager.readCommand();
            runCommand(pair.getCommand(), pair.getArg());
        }
    }
}