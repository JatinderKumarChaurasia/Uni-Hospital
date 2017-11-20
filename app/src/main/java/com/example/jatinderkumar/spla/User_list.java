package com.example.jatinderkumar.spla;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.support.v7.util.AsyncListUtil;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Adapter.UserRecycleAdapter;
import Model.User;
import Sql.DatabaseHolder;

public class User_list extends AppCompatActivity {

    AppCompatActivity activity;
    TextView txtName;
    RecyclerView recyclerView;
    List<User> userList;
    UserRecycleAdapter userRecycleAdapter;
    DatabaseHolder databaseHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_user_list );
        initViews();
        initObjects();
    }
    private void initViews()
    {
        txtName =(TextView) findViewById( R.id.txtName );
        recyclerView =(RecyclerView) findViewById( R.id.recyclerViewUsers );
    }
    private  void  initObjects()
    {
        userList =new ArrayList<>(  );
        userRecycleAdapter = new UserRecycleAdapter( userList );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setItemAnimator( new DefaultItemAnimator() );
        recyclerView.setHasFixedSize( true );
        recyclerView.setAdapter( userRecycleAdapter );
        databaseHolder = new DatabaseHolder( activity );
        String emailFromIntent = getIntent().getStringExtra( "Email" );
        txtName.setText( emailFromIntent );
        getDataFromSQLite();
    }
    private void getDataFromSQLite()
    {
        new AsyncTask<Void,Void,Void>()
        {
            @Override
            protected Void doInBackground(Void... params) {

                userList.clear();
                userList.addAll(databaseHolder.getAllUser());
                return null;

            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute( aVoid );
                userRecycleAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

}
