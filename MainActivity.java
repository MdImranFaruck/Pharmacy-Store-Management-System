package com.imran.familypharmacy;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

//cd data/data/com.imran.familypharmacy/databases

public class MainActivity extends AppCompatActivity{
    private static final String TAG = "MainActivity";

    private Button mAdminLoginButton;
    private Button mRegisterLoginButton;

    AppDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new AppDatabase(this);
        SQLiteDatabase database = db.getReadableDatabase();


        mAdminLoginButton = findViewById(R.id.btn_registration);
        mAdminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AdminLoginActivity.class);
                startActivity(intent);
            }
        });

        mRegisterLoginButton = findViewById(R.id.btn_login);
        mRegisterLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}