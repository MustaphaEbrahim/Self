package com.example.self.Repository.DataProviders.Base;


public interface OnDataProviderResponseListener<T> {

    void onSuccess(T response);

    void onError(String errorMsg);
}
