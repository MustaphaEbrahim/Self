package com.example.self.UI.JournalList.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.self.UI.CreateAccount.ViewModel.CreateViewModel;
import com.example.self.UI.JournalList.ViewModel.JournalVeiwModel;
import com.example.self.databinding.ActivityCreateAccountBinding;
import com.example.self.databinding.ActivityJournalListBinding;

public class JournalListActivity extends AppCompatActivity {

    private ActivityJournalListBinding binding;
    private JournalVeiwModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJournalListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}