package com.example.self.UI.PostJournal.View;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.self.UI.Base.BaseActivity;
import com.example.self.UI.PostJournal.ViewModel.PostJournalViewModel;
import com.example.self.databinding.ActivityPostJournalBinding;

public class PostJournalActivity extends BaseActivity {

    private ActivityPostJournalBinding binding;
    private PostJournalViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostJournalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bundelfun();
    }

    private void bundelfun() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            String username = bundle.getString("username");
            String userId = bundle.getString("userId");
        }
    }

    @Override
    public void initViewModel() {
        viewModel =new ViewModelProvider(this).get(PostJournalViewModel.class);
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