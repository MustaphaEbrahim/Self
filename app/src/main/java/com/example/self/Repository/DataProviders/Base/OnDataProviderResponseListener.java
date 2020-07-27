package com.example.self.Repository.DataProviders.Base;


import com.example.self.Repository.Server.ResponseBody.Base.Result;

public interface OnDataProviderResponseListener<T> {

    void onSuccess(T response);

    void onError(String errorMsg);
}
