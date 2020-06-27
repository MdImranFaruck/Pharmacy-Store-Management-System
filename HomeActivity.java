package com.imran.familypharmacy;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class HomeActivity extends AppCompatActivity implements AppDialog.DialogEvents {

    private static final String TAG = "HomeActivity";

    private boolean mTwoPane = false;
    private CardView mButtonCategoryOne;
    private CardView mButtonViewAll;
    private CardView mButtonCategoryTwo;
    private CardView mButtonCategoryThree;
    private CardView mButtonCategoryFour;
    private CardView mButtonCategoryFive;
    private CardView mButtonCategorySix;
    private CardView mButtonCategorySeven;
    private CardView mButtonCategoryEight;
    private CardView mButtonCategoryNine;

    public static final String ADD_EDIT_FRAGMENT = "AddEditFragment";
    public static final int BLOCK_QUIT_WITH_BACKBUTTON = 1;

    private AlertDialog mDialog = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mButtonViewAll = findViewById(R.id.view_all);
        mButtonViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewAll();
            }
        });

        mButtonCategoryOne = findViewById(R.id.category1_pain);
        mButtonCategoryOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoryOne();
            }
        });

        mButtonCategoryTwo = findViewById(R.id.category2_skincare);
        mButtonCategoryTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoryTwo();
            }
        });

        mButtonCategoryThree = findViewById(R.id.category3_Oral);
        mButtonCategoryThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoryThree();
            }
        });

        mButtonCategoryFour = findViewById(R.id.category4_food_poison);
        mButtonCategoryFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoryFour();
            }
        });

        mButtonCategoryFive = findViewById(R.id.category5_hepatitis);
        mButtonCategoryFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoryFive();
            }
        });

        mButtonCategorySix = findViewById(R.id.category6_eye_infection);
        mButtonCategorySix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategorySix();
            }
        });

        mButtonCategorySeven = findViewById(R.id.category7_cold);
        mButtonCategorySeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategorySeven();
            }
        });

        mButtonCategoryEight = findViewById(R.id.category9_others);
        mButtonCategoryEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoryEight();
            }
        });

        mButtonCategoryNine = findViewById(R.id.category8_nutrition);
        mButtonCategoryNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoryNine();
            }
        });

    }
    public void openViewAll(){
        Intent intent = new Intent(this, AllMedicines.class);
        startActivity(intent);
    }

    public void openCategoryOne(){
        Intent intent = new Intent(this, CategoryOneMedicineList.class);
        startActivity(intent);
    }

    public void openCategoryTwo(){
        Intent intent = new Intent(this, CategoryTwoMedicineList.class);
        startActivity(intent);
    }

    public void openCategoryThree(){
        Intent intent = new Intent(this, CategoryThreeMedicineList.class);
        startActivity(intent);
    }

    public void openCategoryFour(){
        Intent intent = new Intent(this, CategoryFourMedicineList.class);
        startActivity(intent);
    }

    public void openCategoryFive(){
        Intent intent = new Intent(this, CategoryFiveMedicineList.class);
        startActivity(intent);
    }

    public void openCategorySix(){
        Intent intent = new Intent(this, CategorySixMedicineList.class);
        startActivity(intent);
    }

    public void openCategorySeven(){
        Intent intent = new Intent(this, CategorySevenMedicineList.class);
        startActivity(intent);
    }

    public void openCategoryEight(){
        Intent intent = new Intent(this, CategoryEightMedicineList.class);
        startActivity(intent);
    }

    public void openCategoryNine(){
        Intent intent = new Intent(this, CategoryNineMedicineList.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menumain_settings) {
            return true;
        }
        //This all id come from menu item
        //when user select any item, layout move that item layout
        switch(id){
            case R.id.menumain_addMedicine:
                medicineEditRequest(null);
                break;
            case R.id.menumain_makeReport:
                makeReportClicked();
                break;
            case R.id.menumain_about:
                showAboutDialog();
                break;
            case R.id.menumain_account:
                accountClicked();
                break;
            case R.id.menumain_notifications:
                notificationClicked();
                break;
            case R.id.menumain_message:
                messageClicked();
                break;
            case R.id.menumain_settings:
                break;
            case R.id.menumain_showReport:
                showReportClicked();
                break;
            case R.id.menumain_logout:
                logoutClicked();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("SetTextI18n")
    public void showAboutDialog() {
        @SuppressLint("InflateParams") View messageView = getLayoutInflater().inflate(R.layout.about, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.pharmacy);

        builder.setView(messageView);

        mDialog = builder.create();
        mDialog.setCanceledOnTouchOutside(true);
//        builder.setTitle(R.string.app_name);
//        builder.setIcon(R.mipmap.ic_launcher);

        TextView tv = (TextView) messageView.findViewById(R.id.about_version);
        tv.setText("v" + BuildConfig.VERSION_NAME);

        mDialog.show();
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

    public void showReportClicked(){
        Intent intent = new Intent(this, ShowReports.class);
        startActivity(intent);
    }

    public void makeReportClicked(){
        Intent intent = new Intent(this, MakeReport.class);
        startActivity(intent);
    }

    public void notificationClicked(){
        Intent intent = new Intent(this, AllNotifications.class);
        startActivity(intent);
    }

    public void logoutClicked(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void accountClicked(){
        Intent intent = new Intent(this, Account.class);
        startActivity(intent);
    }

    public void messageClicked(){
        Intent intent = new Intent(this, Message.class);
        startActivity(intent);
    }

    //This method show the toast massage user try to quit with back button
    //This code show dialog, when user press back button without saving
    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: called");
        // show dialogue to get confirmation to quit editing
        AppDialog dialog = new AppDialog();
        Bundle args = new Bundle();
        args.putInt(AppDialog.DIALOG_ID, BLOCK_QUIT_WITH_BACKBUTTON);
        args.putString(AppDialog.DIALOG_MESSAGE, getString(R.string.back_button_message));
//        args.putInt(AppDialog.DIALOG_POSITIVE_RID, R.string.ok);

        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), null);
    }

    @Override
    public void onPositiveDialogResult(int dialogId, Bundle args) {
        Log.d(TAG, "onPositiveDialogResult: called");
    }

    @Override
    public void onNegativeDialogResult(int dialogId, Bundle args) {
        Log.d(TAG, "onNegativeDialogResult: called");
    }

    @Override
    public void onDialogCancelled(int dialogId) {
        Log.d(TAG, "onDialogCancelled: called");
    }

    //Until here
}
