package server.commands;

import common.command.core.Command;
import common.command.core.CommandImpl;
import common.command.core.CommandType;

import java.util.Stack;

import static common.io.OutputManager.print;

public class HistoryCommand extends CommandImpl {
    ServerCommandManager commandManager;

   public HistoryCommand(ServerCommandManager cm) {
       super("history", CommandType.NORMAL);
       commandManager = cm;
   }

    @Override
    public String execute() {
       Stack<String> commandHistory = commandManager.getCommandHistory();
       String text = "";
       if (commandHistory.isEmpty()) {
           text = "history is empty";
       } else
           for (int i=commandHistory.size(); i>0; i--)
               text += commandHistory.get(commandHistory.size() - i) + "\n";
       return text;
   }
}
