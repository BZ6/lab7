package client.main;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import client.client.Client;
import common.exceptions.ConnectionException;
import common.exceptions.InvalidPortException;

import static common.io.OutputManager.*;

public class ClientMain {

    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            printErr("Unsupported encoding UTF-8");
        }

        String address = "localhost";
        String strPort = "4445";
        int port = 0;
        try {
            if (args.length == 2) {
                address = args[0];
                strPort = args[1];
            }
            if(args.length == 1){
                strPort = args[0];
                print("no address passed by arguments, setting default : " + address);
            }
            if(args.length == 0){
                print("no port and no address passed by arguments, setting default :" + address + "/" + strPort);
            }
            try {
                port = Integer.parseInt(strPort);
            } catch (NumberFormatException e) {
                throw new InvalidPortException();
            }
            Client client = new Client(address, port);
            client.run();
        } catch (ConnectionException e) {
            print(e.getMessage());
        }
    }
}
