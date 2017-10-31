package com.example.allan.androidpresencesystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

public class ListOnline extends AppCompatActivity {


    //Firebase
    private DatabaseReference onlineRef, currentRef, counterRef;
    private FirebaseRecyclerAdapter<User, ListOnlineViewHolder> adapter;

    //View
    private Toolbar toolbar;
    private RecyclerView recyclerViewOnline;
    private  RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_online);

        initView();
    }

    private void initView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Allan presence Sytstem");
        setSupportActionBar(toolbar);

        recyclerViewOnline = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewOnline.setLayoutManager(layoutManager);
        recyclerViewOnline.setHasFixedSize(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
