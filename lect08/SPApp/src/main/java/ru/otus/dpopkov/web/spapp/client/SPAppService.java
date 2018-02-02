package ru.otus.dpopkov.web.spapp.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("SPAppService")
public interface SPAppService extends RemoteService {
    // Sample interface method of remote interface
    String getMessage(String msg);

    /**
     * Utility/Convenience class.
     * Use SPAppService.App.getInstance() to access static instance of SPAppServiceAsync
     */
    public static class App {
        private static SPAppServiceAsync ourInstance = GWT.create(SPAppService.class);

        public static synchronized SPAppServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
