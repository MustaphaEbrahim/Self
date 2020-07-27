package com.example.self.UI.CreateAccount.View;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.self.UI.Base.BaseActivity;
import com.example.self.UI.CreateAccount.ViewModel.CreateViewModel;
import com.example.self.UI.PostJournal.View.PostJournalActivity;
import com.example.self.databinding.ActivityCreateAccountBinding;

public class CreateAccountActivity extends BaseActivity {

    private ActivityCreateAccountBinding binding;
    private CreateViewModel viewModel;


    //***********___________*******
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel.getUser();
        initListener();
    }


    private void initListener() {
        binding.CreateAcctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(binding.userNameAcct.getText().toString())
                        && !TextUtils.isEmpty(binding.emailAcct.getText().toString())
                        && !TextUtils.isEmpty(binding.passwordAcct.getText().toString())) {


                    String email = binding.emailAcct.getText().toString().trim();
                    String password = binding.passwordAcct.getText().toString().trim();
                    String username = binding.userNameAcct.getText().toString().trim();

                    viewModel.createUserEmailAccount(email, password, username);
                } else {
                    Toast.makeText(CreateAccountActivity.this, "Empty Fields Not Allowed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void initViewModel() {
        viewModel = new ViewModelProvider(this).get(CreateViewModel.class);
        setViewModel(viewModel);

    }

    @Override
    public void initObservers() {

        viewModel.getIsSuccessMLD().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {

                startPostActivity(user);


            }
        });

        viewModel.getUserAlreadyExistMLD().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {


                startPostActivity(user);

            }
        });


    }

    // User
    private void startPostActivity(User user) {

        Intent intent = new Intent(CreateAccountActivity.this , PostJournalActivity.class);
        intent.putExtra("username", user.getUserName());
        intent.putExtra("userId", user.getUserId());
        startActivity(intent);

    }

    @Override
    public void initErrorObservers() {

        viewModel.getIsErrorMLD().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(CreateAccountActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void initLoadingObservers() {

        viewModel.getIsLoadingMLD().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {

                if (isLoading) {
                    binding.Createacctprogress.setVisibility(View.VISIBLE);
                } else {
                    binding.Createacctprogress.setVisibility(View.GONE);
                }

            }
        });

    }
}