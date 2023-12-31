package command.commands;

import command.core.Command;

import java.util.Stack;

import static io.OutputManager.print;

public class HistoryCommand implements Command {
    private final Stack<String> commandHistory;

   public HistoryCommand(Stack<String> commandHistory){
       this.commandHistory = commandHistory;
   }

   public void run(String arg){
       if (commandHistory.isEmpty()) {
           print("history is empty");
       } else if (commandHistory.size() < 10) {
           for (int i=commandHistory.size(); i>0; i--)
               print(commandHistory.get(commandHistory.size()-i));
       } else{
           for (int i=10; i>0; i--)
               print(commandHistory.get(commandHistory.size()-i));
       }
   }
}
