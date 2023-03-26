package command.commands;

import collection.CollectionManager;
import command.CommandManager;
import command.core.Command;
import data.LabWork;
import exceptions.MissedCommandArgumentException;
import exceptions.RecursiveScriptExecuteException;
import file.ReaderWriter;
import io.InputManager;

import java.util.Stack;

import static io.OutputManager.print;

public class ExecuteScriptCommand implements Command {
    private final InputManager inputManager;
    private final CollectionManager<LabWork> collectionManager;
    private final ReaderWriter fileManager;
    private final String currentScriptFileName;
    private final Stack<String> callStack;
    public ExecuteScriptCommand(InputManager inputManager, CollectionManager<LabWork> collectionManager,
                                ReaderWriter fileManager, String currentScriptFileName, Stack<String> callStack){
        this.inputManager = inputManager;
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
        this.currentScriptFileName = currentScriptFileName;
        this.callStack = callStack;
    }
    public void run(String arg){
        if (arg == null || arg.equals("")) throw new MissedCommandArgumentException();

        if (callStack.contains(currentScriptFileName)) throw new RecursiveScriptExecuteException();

        callStack.push(currentScriptFileName);
        CommandManager process = new CommandManager(collectionManager, inputManager, fileManager);
        process.fileMode(arg);
        callStack.pop();
        print("successfully executed script " + arg);
    }
}
