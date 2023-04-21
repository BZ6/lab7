package client.main;

import java.io.PrintStream;

import client.client.Client;
import common.exceptions.ConnectionException;
import common.exceptions.InvalidPortException;
import common.exceptions.InvalidProgramArgumentsException;

import static common.io.OutputManager.*;

public class Main {

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));

        String addr  = "";
        int port = 0;
        try {
            if (args.length != 2) throw new InvalidProgramArgumentsException("no address passed by arguments");
            addr = args[0];
            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException e){
                throw new InvalidPortException();
            }
            Client client = new Client(addr,port);
            client.start();
        }
        catch (InvalidProgramArgumentsException| ConnectionException e){
            print(e.getMessage());
        }
    }
}