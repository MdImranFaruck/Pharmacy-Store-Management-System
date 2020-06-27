package com.imran.familypharmacy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AllMedicines extends AppCompatActivity implements AllMedicineCursorRecyclerViewAdapter.onMedicineClickListener,
        AppDialog.DialogEvents{
    private static final String TAG = "AllMedicines";

    // Whether or not the activity is in 2-pane mode
    // i.e. running in landscape on a tablet
    private boolean mTwoPane = false;

    //This ID for Dialog when user Delete, show Dialog
    public static final int DELETE_DIALOG_ID = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_medicines);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    //This method for editing task
    @Override
    public void onEditClick(Medicine medicine) {
        medicineEditRequest(medicine);
    }

    //This method for deleting task
    @Override
    public void onDeleteClick(Medicine medicine) {
        AppDialog dialog = new AppDialog();
        Bundle args = new Bundle();
        args.putInt(AppDialog.DIALOG_ID, DELETE_DIALOG_ID);
        args.putString(AppDialog.DIALOG_MESSAGE, getString(R.string.dia_message, medicine.getId(), medicine.getName()));
        args.putInt(AppDialog.DIALOG_POSITIVE_RID, R.string.dia_delete);

        args.putLong("MedicineId", medicine.getId());

        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), null);
        //This line has to remove
//        getContentResolver().delete(MedicinesContract.buildMedicineUri(medicine.getId()), null, null);
    }

    //This method is for add and editing medicine
    //This method also make connection with activity_add_edit layout
    private void medicineEditRequest(Medicine medicine){
        Log.d(TAG, "medicineEditRequest: starts");
        if(mTwoPane){
            Log.d(TAG, "medicineEditRequest: in two_pane mode(tablet)");
        }else {
            Log.d(TAG, "medicineEditRequest: in single_pane mode(phone)");
            //In single pane mode start the details activity for the selected item id.
            Intent detailIntent = new Intent(this, AddEditActivity.class);
            if(medicine != null){//editing the task
                detailIntent.putExtra(Medicine.class.getSimpleName(), medicine);
                startActivity(detailIntent);
            }else {//adding new task
                startActivity(detailIntent);
            }
        }
    }

    @Override
    public void onPositiveDialogResult(int dialogId, Bundle args) {
        Log.d(TAG, "onPositiveDialogResult: called");
        Long medicineId = args.getLong("MedicineId");

        if(BuildConfig.DEBUG && medicineId == 0) {
            throw new AssertionError("Medicine Id is Zero");
        }
        getContentResolver().delete(MedicinesContract.buildMedicineUri(medicineId), null, null);
    }

    @Override
    public void onNegativeDialogResult(int dialogId, Bundle args) {
        Log.d(TAG, "onNegativeDialogResult: called");
    }

    @Override
    public void onDialogCancelled(int dialogId) {
        Log.d(TAG, "onDialogCancelled: called");
    }

    //    This method for menu Item
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_all__medicines, menu);
        return true;
    }

    //This method for menu Item
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
