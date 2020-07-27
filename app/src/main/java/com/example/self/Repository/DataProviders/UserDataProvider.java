package com.example.self.Repository.DataProviders;


import androidx.annotation.NonNull;

import com.example.self.Repository.DataProviders.Base.BaseDataProvider;
import com.example.self.Repository.DataProviders.Base.OnDataProviderResponseListener;

import com.example.self.UI.CreateAccount.View.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UserDataProvider extends BaseDataProvider {
    public static UserDataProvider sharedInstance = new UserDataProvider();

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;

    //fireStore connection
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("Users");




    public void login(String type, int year, int fnUnit, int languageId, int branchNo, int userId, String password, OnDataProviderResponseListener<Boolean> responseListener) {

/*        webServiceConsumer.callService(webServiceConsumer.getDistTrackApi().Login(type, year, fnUnit, languageId, branchNo, userId, password), new OnDataProviderResponseListener<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse response) {
                User user = new User();
                user.setBranch(branchNo);
                user.setCode(response.getLoginResponseBody().getCode());
                user.setFnYear(fnUnit);
                user.setUserId(userId);
                user.setUserName(response.getLoginResponseBody().getLoggedInUserName());
                user.setYear(year);
                user.setSupperRepresentativeCode(response.getLoginResponseBody().getSupperRepresentativeCode());
                getDao().deleteAll();
                getDao().insert(user);
                responseListener.onSuccess(true);

            }

            @Override
            public void onError(ResponseError responseError) {

                responseListener.onError(responseError);

            }

        });*/
    }

/*
    public void getUser(OnDataProviderResponseListener<User> responseListener) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                responseListener.onSuccess(getDao().getUser());
            }
        });
    }
*/


    public void createUserEmailAccount(String email, String password, String username, OnDataProviderResponseListener<User> booleanOnDataProviderResponseListener) {

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                currentUser = firebaseAuth.getCurrentUser();
                assert currentUser != null;
                String currentUserId = currentUser.getUid();

                User user = new User();
                user.setUserName(username);
                user.setUserId(currentUserId);



                collectionReference.add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {


                        booleanOnDataProviderResponseListener.onSuccess(user);



                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        booleanOnDataProviderResponseListener.onError(e.getLocalizedMessage());
                    }
                });
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                booleanOnDataProviderResponseListener.onError(e.getLocalizedMessage());
            }
        });

    }

    public void getUser(OnDataProviderResponseListener<User> userOnDataProviderResponseListener) {

        firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser = firebaseAuth.getCurrentUser();

                if (currentUser != null){//user is already loggin...

                    User user = new User();
                    user.setUserId(currentUser.getUid());
                    user.setUserName(currentUser.getDisplayName());

                    userOnDataProviderResponseListener.onSuccess(user);



                }else{//no user yet...

                }
            }
        });



        /*authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser = firebaseAuth.getCurrentUser();

                if (currentUser != null){//user is already loggin...

                    User user = new User();
                    user.setUserId(currentUser.getUid());
                    user.setUserName(currentUser.getDisplayName());

                    userOnDataProviderResponseListener.onSuccess(user);



                }else{//no user yet...

                }
            }
        };*/



    }
}

