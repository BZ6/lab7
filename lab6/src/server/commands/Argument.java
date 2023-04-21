package server.commands;

import common.connection.Request;
import common.data.LabWork;


public class Argument implements Request {
    private String arg;
    private LabWork labWork;
    public Argument(String s, LabWork w){
        arg = s;
        labWork = w;
    }
    public String getStringArg(){
        return arg;
    }
    public LabWork getLabWork(){
        return labWork;
    }
    public String getCommandName(){
        return "";
    }
}