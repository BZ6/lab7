package server.main;

import common.exceptions.ConnectionException;
import common.exceptions.DatabaseException;
import common.exceptions.InvalidPortException;
import server.log.Log;
import server.server.Server;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import static common.io.OutputManager.printErr;

/**
 * main class for launching server with arguments
 */
public class ServerMain {
    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            Log.logger.error(e.getMessage());
        }
        int port = 4445;
        String strPort = "4445";
        String dbHost = "pg";
        String user = "s367224";
        String password = "qDGanj9ZWdzjman6";
        String url = "jdbc:postgresql://" + dbHost + ":5432/studs";

        try {
            if (args.length == 4) {
                strPort = args[0];
                dbHost = args[1];
                user = args[2];
                password = args[3];
                url = "jdbc:postgresql://" + dbHost + ":5432/studs";
            }
            if (args.length == 1) strPort = args[0];
            if (args.length == 0) Log.logger.info("no port passed by argument, hosted on " + strPort);
            try {
                port = Integer.parseInt(strPort);
            } catch (NumberFormatException e) {
                throw new InvalidPortException();
            }
            Properties settings = new Properties();
            settings.setProperty("url", url);
            settings.setProperty("user", user);
            settings.setProperty("password", password);
            Server server = new Server(port, settings);

            server.run();

        } catch (ConnectionException | DatabaseException e) {
            Log.logger.error(e.getMessage());
        }
    }
}
