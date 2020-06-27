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

public class RegisterActivity extends AppCompatActivity implements AppDialog.DialogEvents {

    private static final String TAG = "RegisterActivity";

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button mRegistrationButton, mLoginButton;
    EditText mResFirstName, mResLastName, mResPassword, mResEmail, mResPhone;

    public static final int EXIT_DIALOG_ID = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        openHelper = new AppDatabase(this);

        mRegistrationButton = findViewById(R.id.res_btn_register);
        mResFirstName = findViewById(R.id.res_first_name);
        mResLastName = findViewById(R.id.res_last_name);
        mResPassword = findViewById(R.id.res_password);
        mResEmail = findViewById(R.id.res_email);
        mResPhone = findViewById(R.id.res_phone);



        mRegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openHelper.getWritableDatabase();
                String fname = mResFirstName.getText().toString();
                String lname = mResLastName.getText().toString();
                String pass = mResPassword.getText().toString();
                String email = mResEmail.getText().toString();
                String phone = mResPhone.getText().toString();
                insertData(fname, lname, pass, email, phone);
                Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void insertData(String fname, String lname, String pass, String email, String phone){
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppDatabase.COL_2, fname);
        contentValues.put(AppDatabase.COL_3, lname);
        contentValues.put(AppDatabase.COL_4, pass);
        contentValues.put(AppDatabase.COL_5, email);
        contentValues.put(AppDatabase.COL_6, phone);
        long id = db.insert(AppDatabase.TABLE_NAME, null, contentValues);
    }


    @Override
    public void onBackPressed() {
        // show dialogue to get confirmation to quit application
        AppDialog dialog = new AppDialog();
        Bundle args = new Bundle();
        args.putInt(AppDialog.DIALOG_ID, EXIT_DIALOG_ID);
        args.putString(AppDialog.DIALOG_MESSAGE, getString(R.string.exit_Diag_message));
        args.putInt(AppDialog.DIALOG_POSITIVE_RID, R.string.exit_Diag_positive_caption);
        args.putInt(AppDialog.DIALOG_NEGATIVE_RID, R.string.exit_Diag_negative_caption);

        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), null);
    }

    @Override
    public void onPositiveDialogResult(int dialogId, Bundle args) {
        //finishAffinity() close all the activity in the applications
        finishAffinity(); // Close all activites
        System.exit(0);  // Releasing resources
    }

    @Override
    public void onNegativeDialogResult(int dialogId, Bundle args) {

    }

    @Override
    public void onDialogCancelled(int dialogId) {

    }

}
