package ru.otus.dpopkov.web.spapp.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SPAppServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
}
