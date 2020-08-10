package com.example.self.UI.Login.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.self.UI.Base.BaseActivity;
import com.example.self.UI.CreateAccount.View.CreateAccountActivity;
import com.example.self.UI.CreateAccount.View.User;
import com.example.self.UI.Login.ViewModel.LoginViewModel;
import com.example.self.UI.PostJournal.View.PostJournalActivity;
import com.example.self.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel.getUser();
        initListener();
    }

    private void initListener() {
        //Login button
       binding.EmailSignInButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (!TextUtils.isEmpty(binding.email.getText().toString()) && !TextUtils.isEmpty(binding.password.getText().toString())){


                   String email = binding.email.getText().toString().trim();
                   String password = binding.password.getText().toString().trim();

                   viewModel.loginEmailPasswordUser(email, password);

                   //startJournalActivity();

               } else {
                   Toast.makeText(LoginActivity.this, "Empty Fields Not Allowed", Toast.LENGTH_LONG).show();
               }

           }
       });
       //Create button
        binding.CreateAcctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this , CreateAccountActivity.class));
            }
        });
    }

    private void startPostActivity(User user) {

        Intent intent = new Intent(LoginActivity.this , PostJournalActivity.class);
        intent.putExtra("username", user.getUserName());
        intent.putExtra("userId", user.getUserId());
        startActivity(intent);
        finish();

    }

    private void startJournalActivity() {

        Intent intent = new Intent(LoginActivity.this , PostJournalActivity.class);
        startActivity(intent);

    }


    @Override
    public void initViewModel() {
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
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

    @Override
    public void initErrorObservers() {
        viewModel.getIsErrorMLD().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initLoadingObservers() {

        viewModel.getIsLoadingMLD().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    binding.loginProgress.setVisibility(View.VISIBLE);
                }else {
                    binding.loginProgress.setVisibility(View.GONE);
                }
            }
        });
    }
}
