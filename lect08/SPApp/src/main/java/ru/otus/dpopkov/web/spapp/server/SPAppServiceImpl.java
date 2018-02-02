package ru.otus.dpopkov.web.spapp.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.otus.dpopkov.web.spapp.client.SPAppService;

public class SPAppServiceImpl extends RemoteServiceServlet implements SPAppService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}