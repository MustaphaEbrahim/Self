package com.example.self.UI.Login.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.self.UI.Base.BaseActivity;
import com.example.self.UI.CreateAccount.View.CreateAccountActivity;
import com.example.self.UI.Login.ViewModel.LoginViewModel;
import com.example.self.UI.Main.View.MainActivity;
import com.example.self.UI.Main.ViewModel.MainActivityViewModel;
import com.example.self.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

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
