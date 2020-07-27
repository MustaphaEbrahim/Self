package com.example.self.Repository.DataProviders.Base;

import android.content.Context;

import com.example.self.Application.App;
import com.example.self.Repository.Server.Consumer.WebServiceConsumer;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public abstract class BaseDataProvider {

    protected Context context;
    protected Executor executor = Executors.newSingleThreadExecutor();

    public BaseDataProvider() {
        this.context = App.getAppContext();

    }


}
