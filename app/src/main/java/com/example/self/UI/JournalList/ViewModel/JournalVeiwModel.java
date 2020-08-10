package com.example.self.UI.JournalList.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.self.Repository.DataProviders.Base.OnDataProviderResponseListener;
import com.example.self.UI.Base.BaseViewModel;
import com.example.self.UI.CreateAccount.View.User;
import com.example.self.UI.PostJournal.View.Journal;

import java.util.ArrayList;
import java.util.List;

public class JournalVeiwModel extends BaseViewModel {

    private MutableLiveData<Boolean> isSuccessMLD = new MutableLiveData<>();
    private MutableLiveData<String> isErrorMLD = new MutableLiveData<>();
    private MutableLiveData<User> userAlreadyExistMLD = new MutableLiveData<>();
    private List<Journal> journalList = new ArrayList<>();
    private MutableLiveData<Boolean> isJournalsReady = new MutableLiveData<>();

    public JournalVeiwModel(@NonNull Application application) {
        super(application);
    }
    public void signOut() {

        getUserDataProvider().signOut(new OnDataProviderResponseListener<Boolean>() {
            @Override
            public void onSuccess(Boolean response) {
                isSuccessMLD.setValue(response);
            }

            @Override
            public void onError(String errorMsg) {

                isErrorMLD.setValue(errorMsg);

            }
        });

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

    public MutableLiveData<Boolean> getIsSuccessMLD() {
        return isSuccessMLD;
    }
    public MutableLiveData<String> getIsErrorMLD() { return isErrorMLD; }
    public MutableLiveData<User> getUserAlreadyExistMLD() {
        return userAlreadyExistMLD;
    }

    public void getUserJournals(User user) {


        getUserDataProvider().getJournalsList(user, new OnDataProviderResponseListener<List<Journal>>() {
            @Override
            public void onSuccess(List<Journal> response) {
                journalList.clear();
                journalList.addAll(response);
                isJournalsReady.setValue(true);

            }

            @Override
            public void onError(String errorMsg) {

            }
        });


    }

    public MutableLiveData<Boolean> getIsJournalsReady() {
        return isJournalsReady;
    }

    public List<Journal> getJournalList() {
        return journalList;
    }

    public void setJournalList(List<Journal> journalList) {
        this.journalList = journalList;
    }
}
