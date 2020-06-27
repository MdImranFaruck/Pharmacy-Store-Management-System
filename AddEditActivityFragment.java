package com.imran.familypharmacy;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddEditActivityFragment extends Fragment {

    private static final String TAG = "AddEditActivityFragment";

    //enam check fragment being used to add or edit
    public enum fragmentEditMode {EDIT, ADD}
    //We need a private field to track them
    private fragmentEditMode mMode;
    private EditText mNameTextView;
    private EditText mCompanyTextView;
    private EditText mLocationTextView;
    private EditText mPriceTextView;
    private EditText mCategoryTextView;
    private Button mSaveButton;
    private OnSaveClicked mSaveListener = null;

    //This interface for removing layout after clicking save button
    interface OnSaveClicked{
        void onSaveClicked();
    }

    public AddEditActivityFragment() {
        Log.d(TAG, "AddEditActivityFragment: constructor called");
    }

    public boolean canClose(){
        return false;
    }

    //This onAttach for removing layout after clicking save button
    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach: starts");
        super.onAttach(context);

        Activity activity = getActivity();
        if(!(activity instanceof OnSaveClicked)){
            throw new ClassCastException(activity.getClass().getSimpleName()
                    + " must implement AddEditActivityFragment.OnSaveClicked interface");
        }
        mSaveListener = (OnSaveClicked) activity;
    }

    //when you implement onAttach, you have implement onDetach also
    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach: starts");
        super.onDetach();
        mSaveListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: starts");
        //Save all the field in the View from fragment_add_edit layout
        View view =  inflater.inflate(R.layout.fragment_add_edit, container, false);
        mNameTextView = view.findViewById(R.id.addedit_name);
        mCompanyTextView = view.findViewById(R.id.addedit_company);
        mLocationTextView = view.findViewById(R.id.addedit_location);
        mPriceTextView = view.findViewById(R.id.addedit_price);
        mCategoryTextView = view.findViewById(R.id.addedit_category);
        mSaveButton = view.findViewById(R.id.addedit_save);

//        Bundle arguments = getActivity().getIntent().getExtras(); // This has to change letter
        Bundle arguments = getArguments();
        final Medicine medicine;

        if(arguments != null){
            Log.d(TAG, "onCreateView: retrieving medicine details.");

            medicine = (Medicine) arguments.getSerializable(Medicine.class.getSimpleName());
            if(medicine != null){ // Editing
                Log.d(TAG, "onCreateView: Editing Mode");
                mNameTextView.setText(medicine.getName());
                mCompanyTextView.setText(medicine.getCompany());
                mLocationTextView.setText(medicine.getLocation());
                mPriceTextView.setText(medicine.getPrice());
//                mCategoryTextView.setText(medicine.getCategory());
                mMode = fragmentEditMode.EDIT;
            }else { //Adding new medicine
                mMode = fragmentEditMode.ADD;
            }
        }else {
            medicine = null;
            Log.d(TAG, "onCreateView: No arguments, adding new medicine");
            mMode = fragmentEditMode.ADD;
        }

        //This code for save button
        //when user click save button, it will save medicine to database
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update the database, if anythings change in field

                //Save repeated conversion to int
                int so;
                if(mCategoryTextView.length() > 0){
                    so = Integer.parseInt(mCategoryTextView.getText().toString());
                }else {
                    so = 0;
                }

                ContentResolver contentResolver = getActivity().getContentResolver();
                ContentValues values = new ContentValues();

                switch (mMode){
                    case EDIT:
                        if(!mNameTextView.getText().toString().equals(medicine.getName())){
                            values.put(MedicinesContract.Columns.MEDICINES_NAME, mNameTextView.getText().toString());
                        }
                        if(!mCompanyTextView.getText().toString().equals(medicine.getCompany())){
                            values.put(MedicinesContract.Columns.MEDICINES_COMPANY, mCompanyTextView.getText().toString());
                        }
                        if(!mLocationTextView.getText().toString().equals(medicine.getLocation())){
                            values.put(MedicinesContract.Columns.MEDICINES_LOCATION, mLocationTextView.getText().toString());
                        }
                        if(!mPriceTextView.getText().toString().equals(medicine.getPrice())){
                            values.put(MedicinesContract.Columns.MEDICINES_PRICE, mPriceTextView.getText().toString());
                        }
                        if(so != medicine.getCategory()){
                            values.put(MedicinesContract.Columns.MEDICINES_CATEGORY, so);
                        }
                        if(values.size() != 0){
                            Log.d(TAG, "onClick: updating medicine");
                            contentResolver.update(MedicinesContract.buildMedicineUri(medicine.getId()), values, null, null);
                        }
                        break;
                    case ADD:
                        if(mNameTextView.length() > 0){
                            values.put(MedicinesContract.Columns.MEDICINES_NAME, mNameTextView.getText().toString());
                            values.put(MedicinesContract.Columns.MEDICINES_COMPANY, mCompanyTextView.getText().toString());
                            values.put(MedicinesContract.Columns.MEDICINES_LOCATION, mLocationTextView.getText().toString());
                            values.put(MedicinesContract.Columns.MEDICINES_PRICE, mPriceTextView.getText().toString());
                            values.put(MedicinesContract.Columns.MEDICINES_CATEGORY, so);
                            contentResolver.insert(MedicinesContract.CONTENT_URI, values);
                        }
                        break;
                }
                Log.d(TAG, "onClick: Done editing");

                //This code for removing layout after clicking save button
                if(mSaveListener != null){
                    mSaveListener.onSaveClicked();
                }
            }
        });
        Log.d(TAG, "onCreateView: Exiting.....");

        return view;
    }
}
