package client.client;

import common.connection.AnswerMsg;
import common.connection.Request;
import common.connection.Response;
import common.exceptions.ConnectionException;
import common.exceptions.ConnectionTimeoutException;
import common.exceptions.InvalidDataException;

public class Asker {
    private Client client;
    public Asker(Client client){
        this.client = client;
    }

    public AnswerMsg ask(Request req) {
        AnswerMsg res = new AnswerMsg();
        try {
            if(client.getUser() != null && req.getUser() == null) req.setUser(client.getUser());
            client.send(req);
            res = (AnswerMsg) client.receive();
            if(res.getStatus() == Response.Status.AUTH_SUCCESS) {
                client.setUser(req.getUser());
            }
            if(res.getStatus() == Response.Status.LOGOUT) {
                client.setUser(null);
            }
        } catch (ConnectionTimeoutException e) {
            res.info("no attempts left");
        } catch (InvalidDataException | ConnectionException e) {
            res.error(e.getMessage());
        }

        return res;
    }
}
