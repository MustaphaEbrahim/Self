package com.mustafa.self.UI.PostJournal.ViewModel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.mustafa.self.Repository.DataProviders.Base.OnDataProviderResponseListener;
import com.mustafa.self.UI.Base.BaseViewModel;
import com.mustafa.self.UI.CreateAccount.View.User;
import com.mustafa.self.UI.PostJournal.View.Journal;

public class PostJournalViewModel extends BaseViewModel {

    private MutableLiveData<Boolean> isLoadingMLD = new MutableLiveData<>();
    private MutableLiveData<Journal> isSuccessMLD = new MutableLiveData<>();
    private MutableLiveData<String> isErrorMLD = new MutableLiveData<>();
    private MutableLiveData<User> userAlreadyExistMLD = new MutableLiveData<>();
    private User currentUser;


    public PostJournalViewModel(@NonNull Application application) {
        super(application);
    }

    public void saveJournal(String title, String thoughts, Uri imageUri) {
        isLoadingMLD.setValue(true);
        if (currentUser != null) {
            getUserDataProvider().saveJournal(title, thoughts, imageUri, currentUser, new OnDataProviderResponseListener<Journal>() {
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

    public void getUserJournal() {
        getUserDataProvider().getUserPostJournal(new OnDataProviderResponseListener<User>() {
            @Override
            public void onSuccess(User user) {
                currentUser = user;
                userAlreadyExistMLD.setValue(user);
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
