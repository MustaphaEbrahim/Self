package com.example.self.UI.PostJournal.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.self.Application.App;
import com.example.self.UI.Base.BaseActivity;
import com.example.self.UI.JournalList.View.Journal;
import com.example.self.UI.JournalList.View.JournalListActivity;
import com.example.self.UI.PostJournal.ViewModel.PostJournalViewModel;
import com.example.self.databinding.ActivityPostJournalBinding;

public class PostJournalActivity extends BaseActivity {

    //************************************




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

        //*******************






       /* if (App.getInstance() != null) {
          binding.postUsernameTextView.setText(currentUserName);
        }*/


        initListener();
        //bundelfun();
    }

    private void initListener() {
        binding.postCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getimage from camera

                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);
            }
        });
        binding.postSveJournalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                saveJournal();



                String title = binding.postTitleEditText.getText().toString().trim();
                String thoughts = binding.postDescriptionET.getText().toString().trim();


                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(thoughts)){
                    viewModel.saveJournal(title,thoughts,imageUri);

                } else {
                    Toast.makeText(PostJournalActivity.this, "Empty Fields Not Allowed", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


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

              //  startsaveActivity(user);
                Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();


            }
        });

        viewModel.getUserAlreadyExistMLD().observe(this, new Observer<App>() {
            @Override
            public void onChanged(App user) {


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

            private void startsaveActivity(App app) {

                Intent intent = new Intent(PostJournalActivity.this , JournalListActivity.class);
                intent.putExtra("username", app.getUserName());
                intent.putExtra("userId", app.getId());
                intent.putExtra("imageUri" , app.getImageUrl());
                intent.putExtra("time", app.getTimeAdded());
                intent.putExtra("tittle", app.getTitle());
                intent.putExtra("thought" , app.getThought());
                startActivity(intent);
                finish();

            }
}