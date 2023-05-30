package common.connection;

import common.auth.User;
import common.data.LabWork;

/**
 * Message witch include command and arguments
 */
public class CommandMsg implements Request{
    private final String commandName;
    private final String commandStringArgument;
    private LabWork labWork;
    private User user;
    private Request.Status status;

    public CommandMsg(String commandNm, String commandSA, LabWork lw) {
        commandName = commandNm;
        commandStringArgument = commandSA;
        labWork = lw;
        user = null;
        status = Status.DEFAULT;
    }

    public CommandMsg(String commandNm, String commandSA, LabWork lw, User usr) {
        commandName = commandNm;
        commandStringArgument = commandSA;
        labWork = lw;
        user = usr;
        status = Status.DEFAULT;
    }

    public void setStatus(Status s) {
        status = s;
    }

    public Status getStatus() {
        return status;
    }

    public void setUser(User usr) {
        user = usr;
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
        return labWork;
    }

    public User getUser() {
        return user;
    }

    /**
     * @param lw Command object argument
     */
    public void setLabWork(LabWork lw) {
        labWork = lw;
    }
}
