package com.imran.familypharmacy;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements AppDialog.DialogEvents {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button mBtnLogin;
    public EditText mLoginEmail, mLoginPassword;
    Cursor cursor;

    public static final int EXIT_DIALOG_ID = 1;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openHelper = new AppDatabase(this);
        db = openHelper.getReadableDatabase();
        mBtnLogin = findViewById(R.id.login_btn_login);
        mLoginEmail = findViewById(R.id.login_email);
        mLoginPassword = findViewById(R.id.login_password);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mLoginEmail.getText().toString();
                String pass = mLoginPassword.getText().toString();
                cursor = db.rawQuery("Select * from " + AppDatabase.TABLE_NAME+" Where " + AppDatabase.COL_5+" =? AND "+AppDatabase.COL_4+" =? ", new String[]{email,pass});
                if(cursor != null){
                    if(cursor.getCount() > 0){
                        cursor.moveToNext();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "Error: Your Password or Email Address is wrong", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
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
