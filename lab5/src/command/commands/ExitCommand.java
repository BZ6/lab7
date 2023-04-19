package command.commands;

import command.core.Command;
import utils.Bool;

public class ExitCommand implements Command {
    private static final Boolean STOP_RUNNING = false;
    private final Bool isRunning;
    public ExitCommand(Bool isRunning){
        this.isRunning = isRunning;
    }
    public void run(String arg){
        isRunning.setValue(STOP_RUNNING);
    }
}
