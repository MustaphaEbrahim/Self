package com.example.self.UI.Main.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.self.UI.Base.BaseActivity;
import com.example.self.UI.Login.View.LoginActivity;
import com.example.self.UI.Main.ViewModel.MainActivityViewModel;
import com.example.self.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initListener();

    }

    private void initListener() {
        binding.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //we go to Login Activity
                startActivity(new Intent(MainActivity.this , LoginActivity.class));
            }
        });
    }
    @Override
    public void initViewModel() {
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
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
