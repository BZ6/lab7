package server.commands;

import common.command.core.CommandImpl;
import common.command.core.CommandType;
import server.auth.UserManager;
import common.connection.AnswerMsg;
import common.connection.Response;
import common.exceptions.AuthException;

public class LogoutCommand extends CommandImpl {
    private final UserManager userManager;

    public LogoutCommand(UserManager manager) {
        super("logout", CommandType.AUTH);
        userManager = manager;
    }

    @Override
    public Response run() throws AuthException {
        getArgument().setUser(null);
        return new AnswerMsg().info("logout successful").setStatus(Response.Status.LOGOUT);
    }
}

