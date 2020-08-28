package com.mustafa.self.Repository.DataProviders.Base;

import android.content.Context;

import com.mustafa.self.Application.App;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public abstract class BaseDataProvider {

    protected Context context;
    protected Executor executor = Executors.newSingleThreadExecutor();

    public BaseDataProvider() {
        this.context = App.getAppContext();

    }


}
