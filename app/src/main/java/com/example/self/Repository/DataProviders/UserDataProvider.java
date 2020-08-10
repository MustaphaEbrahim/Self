package com.example.self.Repository.DataProviders;


import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.self.Repository.DataProviders.Base.BaseDataProvider;
import com.example.self.Repository.DataProviders.Base.OnDataProviderResponseListener;

import com.example.self.UI.CreateAccount.View.User;
import com.example.self.UI.PostJournal.View.Journal;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDataProvider extends BaseDataProvider {
    public static UserDataProvider sharedInstance = new UserDataProvider();


    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;
    private FirebaseUser user;


    //fireStore connection
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private CollectionReference userCollectionReference = db.collection("Users");
    private CollectionReference journalCollectionReference = db.collection("Journal");


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
    public void saveJournal(String title, String thoughts, Uri imageUri, User user, OnDataProviderResponseListener<Journal> booleanOnDataProviderResponseListener) {
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


                                String currentUserName = user.getUserName();
                                String currentUserId = user.getUserId();
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
                                journalCollectionReference.add(journal)
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

    public void getUserPostJournal(OnDataProviderResponseListener<User> userOnDataProviderResponseListener) {


        firebaseAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                user = firebaseAuth.getCurrentUser();
                if (user != null) {


                    userCollectionReference.document(user.getUid()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                            if (value != null) {
                                User user = value.toObject(User.class);

                                if (user != null) {
                                    userOnDataProviderResponseListener.onSuccess(user);
                                }

                            }

                        }
                    });


                }
            }
        });
    }

    public void signOut(OnDataProviderResponseListener<Boolean> booleanOnDataProviderResponseListener) {
        firebaseAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (user != null) {
                    firebaseAuth.signOut();
                    booleanOnDataProviderResponseListener.onSuccess(true);
                }
            }
        });

    }

    public void createUserEmailAccount(String email, String password, String username, OnDataProviderResponseListener<User> booleanOnDataProviderResponseListener) {

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                currentUser = firebaseAuth.getCurrentUser();
                assert currentUser != null;
                String currentUserId = currentUser.getUid();

                User user = new User();
                user.setUserName(username);
                user.setUserId(currentUserId);


                userCollectionReference.document(user.getUserId()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

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

    public void loginEmailPasswordUser(String email, String password, OnDataProviderResponseListener<User> booleanOnDataProviderResponseListener) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser currentUser = authResult.getUser();
                assert currentUser != null;
                String currentUserId = currentUser.getUid();

                userCollectionReference.document(currentUserId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        User user = documentSnapshot.toObject(User.class);
                        if (user != null) {
                            booleanOnDataProviderResponseListener.onSuccess(user);
                        }


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        booleanOnDataProviderResponseListener.onError(e.getLocalizedMessage());

                    }
                });

        /*        userCollectionReference.whereEqualTo("userId", currentUserId).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (value != null){
                            User user = new User();

                            for (QueryDocumentSnapshot snapshots : value){
                                user.setUserName(snapshots.getString("userName"));
                                user.setUserId(snapshots.getString("userId"));
                            }




                        }
                    }
                });

*/


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                booleanOnDataProviderResponseListener.onError(e.getLocalizedMessage());
            }
        });
    }

    public void getJournalsList(User user, OnDataProviderResponseListener<List<Journal>> userOnDataProviderResponseListener) {

        List<Journal> journalList = new ArrayList<>();

        journalCollectionReference.whereEqualTo("userId", user.getUserId()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    for (QueryDocumentSnapshot journals : queryDocumentSnapshots) {
                        Journal journal = journals.toObject(Journal.class);
                        journalList.add(journal);
                    }
                    userOnDataProviderResponseListener.onSuccess(journalList);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


    }

    public void getUser(OnDataProviderResponseListener<User> userOnDataProviderResponseListener) {

        firebaseAuth = FirebaseAuth.getInstance();

        currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null) {//user is already loggin...

            User user = new User();
            user.setUserId(currentUser.getUid());
            user.setUserName(currentUser.getDisplayName());

            userOnDataProviderResponseListener.onSuccess(user);


        }



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

