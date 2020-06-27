package com.imran.familypharmacy;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MakeReport extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;

    private EditText mPharmacyIDEditText;
    private EditText mPharmacyLocationEditText;
    private EditText mPharmacistNameEditText;
    private EditText mEmailEditText;
    private EditText mEmergencyNoteEditText;
    private EditText mSubjectEditText;
    private EditText mReportDetailsEditText;
    private Button mSubmitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_report);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        openHelper = new AppDatabase(this);


        mPharmacyIDEditText = findViewById(R.id.rep_pharmacy_id);
        mPharmacyLocationEditText = findViewById(R.id.rep_pharmacy_location);
        mPharmacistNameEditText = findViewById(R.id.rep_pharmacist_name);
        mEmailEditText = findViewById(R.id.rep_email_address);
        mEmergencyNoteEditText = findViewById(R.id.rep_emergency_note);
        mSubjectEditText = findViewById(R.id.rep_subject);
        mReportDetailsEditText = findViewById(R.id.rep_details);
        mSubmitButton = findViewById(R.id.rep_btn_send);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openHelper.getWritableDatabase();
                String pharmID = mPharmacyIDEditText.getText().toString();
                String pharmLoc = mPharmacyLocationEditText.getText().toString();
                String pharmaName = mPharmacistNameEditText.getText().toString();
                String email = mEmailEditText.getText().toString();
                String emergency = mEmergencyNoteEditText.getText().toString();
                String subject = mSubjectEditText.getText().toString();
                String repDel = mReportDetailsEditText.getText().toString();
                insertData(pharmID, pharmLoc, pharmaName, email, emergency, subject, repDel);
                Toast.makeText(getApplicationContext(), "Report successful Created", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MakeReport.this, ShowReports.class);
                startActivity(intent);
            }
        });

    }

    public void insertData(String pharmID, String pharmLoc, String pharmaName, String email, String emergency,
                           String subject, String repDel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppDatabase.COL_7, pharmID);
        contentValues.put(AppDatabase.COL_8, pharmLoc);
        contentValues.put(AppDatabase.COL_9, pharmaName);
        contentValues.put(AppDatabase.COL_10, email);
        contentValues.put(AppDatabase.COL_11, emergency);
        contentValues.put(AppDatabase.COL_12, subject);
        contentValues.put(AppDatabase.COL_13, repDel);
        long id = db.insert(AppDatabase.TABLE_NAME_R, null, contentValues);
    }

}
