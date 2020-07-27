package com.mustafa.self.UI.Login.View;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mustafa.self.UI.Base.BaseActivity;
import com.mustafa.self.UI.CreateAccount.View.CreateAccountActivity;
import com.mustafa.self.UI.Login.ViewModel.LoginViewModel;
import com.mustafa.self.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initListener();
    }

    private void initListener() {
        //Login button
       binding.EmailSignInButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

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

    @Override
    public void initViewModel() {
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        setViewModel(viewModel);
    }

    @Override
    public void initObservers() {

    }

    @Override
    public void initErrorObservers() {

    }

    @Override
    public void initLoadingObservers() {

    }
}
