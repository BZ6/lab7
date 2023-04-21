package server.main;

import common.exceptions.ConnectionException;
import common.exceptions.InvalidPortException;
import common.exceptions.InvalidProgramArgumentsException;
import server.server.Server;

import static common.io.OutputManager.print;

/**
 * main class for launching server with arguments
 */
public class Main {
    public static void main(String[] args) throws Exception {
        int port = 0;
        String strPort = "";
        String path = "labWork_database";
        try {
            if(args.length >= 2) {
                path = args[1];
                strPort = args[0];
            }
            if (args.length == 1) strPort = args[0];
            if(args.length == 0) throw new InvalidProgramArgumentsException("no address passed by arguments");
            try {
                port = Integer.parseInt(strPort);
            } catch (NumberFormatException e){
                throw new InvalidPortException();
            }
            Server server = new Server(port, path);
            server.start();
            server.consoleMode();

        }
        catch (InvalidProgramArgumentsException| ConnectionException e){
            print(e.getMessage());
        }
    }
}