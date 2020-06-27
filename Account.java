package com.imran.familypharmacy;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Account extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button mBackToHomeButton;
    TextView mFirstName, mLastName, mPhone, mEmail;
    Cursor cursor;

    public String holdEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        openHelper = new AppDatabase(this);
        db = openHelper.getReadableDatabase();
        mBackToHomeButton = findViewById(R.id.account_back_to_home_btn);
        mFirstName = findViewById(R.id.account_first_name);
        mLastName = findViewById(R.id.account_last_name);
        mPhone = findViewById(R.id.account_phone);
        mEmail = findViewById(R.id.account_email);

        mBackToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Account.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

}
