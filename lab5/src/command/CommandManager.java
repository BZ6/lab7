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
    private InputManager inputManager;
    private final ReaderWriter fileManager;
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

        commands.addCommand("help", (a)->print(HelpCommand.getHelp()));
        ExecuteScriptCommand exeScrCom = new ExecuteScriptCommand(inputManager, collectionManager,
                                                                  fileManager, currentScriptFileName, callStack);
        commands.addCommand("execute_script", exeScrCom::ExecuteScript);
        InfoCommand infoCom = new InfoCommand(collectionManager);
        commands.addCommand("info", (a)->print(infoCom.getInfo()));
        AddCommand addCom = new AddCommand(inputManager, collectionManager);
        commands.addCommand("add", (a)->addCom.add());
        ShowCommand showCom = new ShowCommand(collectionManager);
        commands.addCommand("show", (a)->showCom.show());
        UpdateCommand updCom = new UpdateCommand(inputManager, collectionManager);
        commands.addCommand("update", updCom::update);
        RemoveByIdCommand remByIdCom = new RemoveByIdCommand(collectionManager);
        commands.addCommand("remove_by_id", remByIdCom::removeById);
        ClearCommand clearCom = new ClearCommand(collectionManager);
        commands.addCommand("clear", (a)->clearCom.clear());
        SaveCommand saveCom = new SaveCommand(collectionManager, fileManager);
        commands.addCommand("save", saveCom::save);
        AddIfMaxCommand addIfMaxCom = new AddIfMaxCommand(inputManager, collectionManager);
        commands.addCommand("add_if_max", (a)->addIfMaxCom.addIfMax());
        RemoveLowerCommand removeLowerCommand = new RemoveLowerCommand(inputManager, collectionManager);
        commands.addCommand("remove_lower", (a)->removeLowerCommand.removeLower());
        MinByPersonalQualitiesMinimumCommand minByPerQuaMinCom = new MinByPersonalQualitiesMinimumCommand(collectionManager);
        commands.addCommand("min_by_personal_qualities_minimum", (a)->minByPerQuaMinCom.minByPersonalQualitiesMinimum());
        MaxByDisciplineCommand maxByDisCom = new MaxByDisciplineCommand(collectionManager);
        commands.addCommand("max_by_discipline", (a)->maxByDisCom.maxByDiscipline());
        FilterStartsWithNameCommand filStarWithNameCom = new FilterStartsWithNameCommand(collectionManager);
        commands.addCommand("filter_starts_with_name", filStarWithNameCom::filterStartsWithNameCommand);
        HistoryCommand hisCom = new HistoryCommand(commands.getCommandHistory());
        commands.addCommand("history", (a)->hisCom.history());
        LoadCommand loadCom = new LoadCommand(collectionManager, fileManager);
        commands.addCommand("load", loadCom::load);
        commands.addCommand("exit", (a)->isRunning=ExitCommand.exit());
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
        runCommand(s,null);
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
        isRunning = true;
        inputManager = new FileInputManager(path);
        isRunning = true;
        while (isRunning && inputManager.getScanner().hasNextLine()){
            CommandWrapper pair = inputManager.readCommand();
            runCommand(pair.getCommand(), pair.getArg());
        }
    }
}