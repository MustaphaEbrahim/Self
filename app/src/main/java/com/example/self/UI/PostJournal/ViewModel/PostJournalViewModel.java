package com.example.self.UI.PostJournal.ViewModel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.self.Application.App;
import com.example.self.Repository.DataProviders.Base.OnDataProviderResponseListener;
import com.example.self.UI.Base.BaseViewModel;
import com.example.self.UI.CreateAccount.View.User;
import com.example.self.UI.JournalList.View.Journal;

public class PostJournalViewModel extends BaseViewModel {

    private MutableLiveData<Boolean> isLoadingMLD = new MutableLiveData<>();
    private MutableLiveData<Journal> isSuccessMLD = new MutableLiveData<>();
    private MutableLiveData<String> isErrorMLD = new MutableLiveData<>();
    private MutableLiveData<App> userAlreadyExistMLD = new MutableLiveData<>();
    private App currentUser;

    public PostJournalViewModel(@NonNull Application application) {
        super(application);
    }
    public void saveJournal(String title , String thoughts, Uri imageUri ){
        isLoadingMLD.setValue(true);

        getUserDataProvider().saveJournal(title,thoughts,imageUri , new OnDataProviderResponseListener<Journal>() {
            @Override
            public void onSuccess(Journal response) {

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
    public MutableLiveData<Journal> getIsSuccessMLD() {
        return isSuccessMLD;
    }

    public MutableLiveData<String> getIsErrorMLD() {
        return isErrorMLD;
    }
    public void getUserJournal(){
        getUserDataProvider().getUserPostJournal(new OnDataProviderResponseListener<App>() {
            @Override
            public void onSuccess(App response) {
                currentUser = response;
                userAlreadyExistMLD.setValue(response);
            }

            @Override
            public void onError(String errorMsg) {

            }
        });
    }
    public MutableLiveData<App> getUserAlreadyExistMLD() {
        return userAlreadyExistMLD;
    }
}
