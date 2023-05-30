package common.connection;

import common.auth.User;
import common.data.LabWork;

import java.io.Serializable;


public interface Request extends Serializable{
    public String getStringArg();
    public LabWork getLabWork();
    public String getCommandName();
    public void setLabWork(LabWork labWork);
    User getUser();
    void setUser(User usr);
    Status getStatus();
    void setStatus(Status s);

    enum Status {
        DEFAULT,
        SENT_FROM_CLIENT,
        RECEIVED_BY_SERVER
    }
}
