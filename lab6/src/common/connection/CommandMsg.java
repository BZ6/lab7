package common.connection;
import java.io.Serializable;

import common.data.LabWork;

/**
 * Message witch include command and arguments
 */
public class CommandMsg implements Request{
    private String commandName;
    private String commandStringArgument;
    private Serializable commandObjectArgument;

    public CommandMsg(String commandNm, String commandSA, Serializable commandOA) {
        commandName = commandNm;
        commandStringArgument = commandSA;
        commandObjectArgument = commandOA;
    }

    /**
     * @return Command name.
     */
    public String getCommandName() {
        return commandName;
    }

    /**
     * @return Command string argument.
     */
    public String getStringArg() {
        return commandStringArgument;
    }

    /**
     * @return Command object argument.
     */
    public LabWork getLabWork() {
        return (LabWork) commandObjectArgument;
    }

    /**
     * @param labWork
     */
    public void setLabWork(LabWork labWork) {
        commandObjectArgument = labWork;
    }
}
