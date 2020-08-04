package com.example.self.Repository.DataProviders;


import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.self.Application.App;
import com.example.self.Repository.DataProviders.Base.BaseDataProvider;
import com.example.self.Repository.DataProviders.Base.OnDataProviderResponseListener;

import com.example.self.UI.CreateAccount.View.User;
import com.example.self.UI.JournalList.View.Journal;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Date;

public class UserDataProvider extends BaseDataProvider {
    public static UserDataProvider sharedInstance = new UserDataProvider();


    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;
    private FirebaseUser user;


    //fireStore connection
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private StorageReference storageReference;
    private CollectionReference collectionReference = db.collection("Users");
    private CollectionReference collectionReferenceStorage = db.collection("Journal");




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
    public void saveJournal(String title , String thoughts ,Uri imageUri , OnDataProviderResponseListener<Journal> booleanOnDataProviderResponseListener){
//        firebaseAuth.
        final StorageReference filepath = storageReference //.../journal_images/our_image.jpeg
                .child("journal_images")
                .child("my_image_" + Timestamp.now().getSeconds()); // my_image_887474737

        filepath.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                        filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {


                                String currentUserName = currentUser.getDisplayName();
                                String currentUserId = currentUser.getUid();
                                String imageUrl = uri.toString();
                                //create a Journal Object - model
                                Journal journal = new Journal();
                                journal.setTitle(title);
                                journal.setThought(thoughts);
                                journal.setImageUrl(imageUrl);
                                journal.setTimeAdded(new Timestamp(new Date()));
                                journal.setUserName(currentUserName);
                                journal.setUserId(currentUserId);

                                //invoke our collectionReference
                                collectionReferenceStorage.add(journal)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {

                                                booleanOnDataProviderResponseListener.onSuccess(journal);
//                                                binding.posrProgressBar.setVisibility(View.INVISIBLE);
//                                                startActivity(new Intent(PostJournalActivity.this,
//                                                        JournalListActivity.class));
//                                                finish();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                booleanOnDataProviderResponseListener.onError(e.getLocalizedMessage());

                                            }
                                        });
                                //Todo: and save a Journal instance.

                            }
                        });


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        booleanOnDataProviderResponseListener.onError(e.getLocalizedMessage());
                    }
                });

    }
    public void getUserPostJournal(OnDataProviderResponseListener<App> userOnDataProviderResponseListener){

        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseAuth = firebaseAuth.getInstance();

        firebaseAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    App app = new App();
                    app.setId(user.getUid());
                    app.setUserName(user.getDisplayName());
                    /*String currentUserId = App.getInstance().getUserId();
                    String currentUserName = App.getInstance().getUsername();
                    */

                    userOnDataProviderResponseListener.onSuccess(app);
                }
            }
        });
    }

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

                App journalApi = new App();
                journalApi.setId(currentUserId);
                journalApi.setUserName(username);





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

