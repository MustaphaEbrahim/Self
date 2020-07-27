package com.mustafa.self.UI.CreateAccount.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.mustafa.self.Repository.DataProviders.Base.OnDataProviderResponseListener;
import com.mustafa.self.UI.Base.BaseViewModel;
import com.mustafa.self.UI.CreateAccount.View.User;

public class CreateViewModel extends BaseViewModel {

    private MutableLiveData<Boolean> isLoadingMLD = new MutableLiveData<>();
    private MutableLiveData<User> isSuccessMLD = new MutableLiveData<>();
    private MutableLiveData<String> isErrorMLD = new MutableLiveData<>();
    private MutableLiveData<User> userAlreadyExistMLD = new MutableLiveData<>();

    public CreateViewModel(@NonNull Application application) {
        super(application);
    }

    public void createUserEmailAccount(String email, String password, String username) {

        isLoadingMLD.setValue(true);

        getUserDataProvider().createUserEmailAccount(email,password,username, new OnDataProviderResponseListener<User>() {
            @Override
            public void onSuccess(User response) {

                isLoadingMLD.setValue(false);
                isSuccessMLD.setValue(response);

            }

            @Override
            public void onError(String errorMsg) {
                isLoadingMLD.setValue(false);
                isErrorMLD.setValue(errorMsg);
            }


        });
    }

    public MutableLiveData<Boolean> getIsLoadingMLD() {
        return isLoadingMLD;
    }

    public MutableLiveData<User> getIsSuccessMLD() {
        return isSuccessMLD;
    }

    public MutableLiveData<String> getIsErrorMLD() {
        return isErrorMLD;
    }

    public void getUser() {

        getUserDataProvider().getUser(new OnDataProviderResponseListener<User>() {
            @Override
            public void onSuccess(User response) {
                userAlreadyExistMLD.setValue(response);
            }

            @Override
            public void onError(String errorMsg) {

            }
        });


    }

    public MutableLiveData<User> getUserAlreadyExistMLD() {
        return userAlreadyExistMLD;
    }
}
