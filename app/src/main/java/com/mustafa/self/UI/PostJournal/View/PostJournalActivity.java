package com.mustafa.self.UI.PostJournal.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;


import com.mustafa.self.UI.Base.BaseActivity;
import com.mustafa.self.UI.CreateAccount.View.User;
import com.mustafa.self.UI.JournalList.View.JournalListActivity;
import com.mustafa.self.UI.PostJournal.ViewModel.PostJournalViewModel;
import com.mustafa.self.databinding.ActivityPostJournalBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.List;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class PostJournalActivity extends BaseActivity {

    private FirebaseUser currentUser;
    //************************************

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    private Uri imageUri;


    //**************************************

    private static final int GALLERY_CODE = 1;
    private static final String TAG = "PostJournalActivity";
    private ActivityPostJournalBinding binding;
    private PostJournalViewModel viewModel;

    //Objects.requireNonNull(getSupportActionBar()).setElevation(0);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostJournalBinding.inflate(getLayoutInflater());
        viewModel.getUserJournal();
        setContentView(binding.getRoot());
        initListener();
    }

    private void initListener() {
        binding.postCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getimage from camera

                checkPermissions();

                /*Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);*/
            }
        });
        binding.postSveJournalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                saveJournal();



                String title = binding.postTitleEditText.getText().toString().trim();
                String thoughts = binding.postDescriptionET.getText().toString().trim();


                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(thoughts) && imageUri != null){
                    viewModel.saveJournal(title,thoughts,imageUri);

                } else {
                    Toast.makeText(PostJournalActivity.this, "Empty Fields Not Allowed", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void checkPermissions() {

        Dexter.withContext(context).withPermissions(READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE,CAMERA).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                if (multiplePermissionsReport.areAllPermissionsGranted()) {
                    CropImage.activity()
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .setAspectRatio(2,1)
                            .start(PostJournalActivity.this);
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();




    }


/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                imageUri = data.getData(); // we have the actual path to the image
                binding.postImageView.setImageURI(imageUri);//show image

            }
        }
    }
*/


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageUri = result.getUri();
                binding.postImageView.setImageURI(imageUri);//show image
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    /*@Override
    protected void onStart() {
        super.onStart();
        user = firebaseAuth.getCurrentUser();
        firebaseAuth.addAuthStateListener(authStateListener);
    }*/

    /*@Override
    protected void onStop() {
        super.onStop();
        if (firebaseAuth != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }*/


//*******************************************************************************************************

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLREY_CODE && requestCode == RESULT_OK){
            if (data != null){
                imageUri = data.getData();
                binding.imageView.setImageURI(imageUri);
            }
        }
    }

    private void bundelfun() {


    }*/

    @Override
    public void initViewModel() {
        viewModel =new ViewModelProvider(this).get(PostJournalViewModel.class);
        setViewModel(viewModel);
    }

    @Override
    public void initObservers() {
        viewModel.getIsSuccessMLD().observe(this, new Observer<Journal>() {
            @Override
            public void onChanged(Journal user) {



              //startsaveActivity(user);
                saveJournal();
                //Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();


            }
        });

        viewModel.getUserAlreadyExistMLD().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {

                binding.postUsernameTextView.setText(user.getUserName());
              //  startsaveActivity(user);

            }
        });
    }

    @Override
    public void initErrorObservers() {
        viewModel.getIsErrorMLD().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(PostJournalActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initLoadingObservers() {

        viewModel.getIsLoadingMLD().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {

                if (isLoading) {
                    binding.posrProgressBar.setVisibility(View.VISIBLE);
                } else {
                    binding.posrProgressBar.setVisibility(View.GONE);
                }

            }
        });
    }

            private void startsaveActivity(Journal app) {

                Intent intent = new Intent(PostJournalActivity.this , JournalListActivity.class);
                intent.putExtra("username", app.getUserName());
                intent.putExtra("userId", app.getUserId());
                intent.putExtra("imageUri" , app.getImageUrl());
                intent.putExtra("time", app.getTimeAdded());
                intent.putExtra("tittle", app.getTitle());
                intent.putExtra("thought" , app.getThought());
                startActivity(intent);
                finish();

            }

            private void saveJournal(){
                Intent intent = new Intent(PostJournalActivity.this , JournalListActivity.class);
                startActivity(intent);
                finish();

            }
}