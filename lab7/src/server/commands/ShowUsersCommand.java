package server.commands;

import common.command.core.CommandImpl;
import common.command.core.CommandType;
import common.exceptions.AuthException;
import server.auth.UserManager;

public class ShowUsersCommand extends CommandImpl {
    private final UserManager userManager;

    public ShowUsersCommand(UserManager userManager) {
        super("show_users", CommandType.SERVER_ONLY);
        this.userManager = userManager;
    }

    @Override
    public String execute() throws AuthException {
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        userManager.getUsers().forEach(user -> builder.append(user).append("\n"));

        return builder.toString();
    }
}
