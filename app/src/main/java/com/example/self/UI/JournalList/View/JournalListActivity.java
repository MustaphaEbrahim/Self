package com.example.self.UI.JournalList.View;


import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.self.Adapters.AdapterJournals;
import com.example.self.R;
import com.example.self.UI.Base.BaseActivity;

import com.example.self.UI.CreateAccount.View.User;
import com.example.self.UI.JournalList.ViewModel.JournalVeiwModel;
import com.example.self.UI.Main.View.MainActivity;
import com.example.self.UI.PostJournal.View.Journal;
import com.example.self.databinding.ActivityJournalListBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class JournalListActivity extends BaseActivity {

    private ActivityJournalListBinding binding;
    private JournalVeiwModel viewModel;
    private AdapterJournals adapterJournals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJournalListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        defineToolbar();
        initRecyclerView();
        viewModel.getUser();
        initListener();

    }

    private void initRecyclerView() {
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(context));
        adapterJournals = new AdapterJournals(viewModel.getJournalList());
        binding.recyclerview.setAdapter(adapterJournals);
    }

    private void defineToolbar() {


        //    final Drawable upArrow = getResources().getDrawable(R.drawable.ic_toolbar_back);
        // upArrow.setColorFilter(getResources().getColor(R.color.grey), PorterDuff.Mode.SRC_ATOP);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        // getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setTitle(context.getString(R.string.app_name));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add:
                //take users to add Journal
                break;
            case R.id.action_signout:
                //sign user out
                viewModel.signOut();

                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void initListener() {

    }


    @Override
    public void initViewModel() {
        viewModel = new ViewModelProvider(this).get(JournalVeiwModel.class);
        setViewModel(viewModel);
    }

    @Override
    public void initObservers() {

        viewModel.getIsSuccessMLD().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                startMainActivity();

            }
        });

        viewModel.getUserAlreadyExistMLD().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                viewModel.getUserJournals(user);
            }
        });

        viewModel.getIsJournalsReady().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                adapterJournals.notifyDataSetChanged();
            }
        });
    }

    private void startMainActivity() {
        Intent intent = new Intent(JournalListActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void initErrorObservers() {

    }

    @Override
    public void initLoadingObservers() {

    }

    @Override
    protected void onStart() {
        super.onStart();

      /*  collectionReference.whereEqualTo("userId" , User.getInstance().getUserId()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()){
                    for (QueryDocumentSnapshot journals : queryDocumentSnapshots){
                        Journal journal = journals.toObject(Journal.class);
                        journalList.add(journal);
                    }
                    //Invoke recyclerView
                    journalRecyclerAdapter = new JournalRecyclerAdapter(JournalListActivity.this, journalList);
                    recyclerView.setAdapter(journalRecyclerAdapter);
                    journalRecyclerAdapter.notifyDataSetChanged();
                } else {
                    binding.listOnThoughts.setVisibility(View.VISIBLE);
                    }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });*/
    }
}