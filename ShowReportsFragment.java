package com.imran.familypharmacy;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.security.InvalidParameterException;

/**
 * A placeholder fragment containing a simple view.
 */
public class ShowReportsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "ShowReportsFragment";

    public static final int LOADER_ID = 0;

    private ShowReportCursorRecyclerViewAdapter mAdapter;

    public ShowReportsFragment() {
        Log.d(TAG, "ShowReportsFragment: Started");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: starts");
        super.onActivityCreated(savedInstanceState);
        LoaderManager.getInstance(this).initLoader(LOADER_ID, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_show_reports, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.all_reports_show);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new ShowReportCursorRecyclerViewAdapter(null);
        recyclerView.setAdapter(mAdapter);

        Log.d(TAG, "onCreateView: returning");
        return view;
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        Log.d(TAG, "onCreateLoader: starts with id " + id);
        String[] projection = {MakeReportContract.Columns._ID,
                MakeReportContract.Columns.PHARMACY_ID,
                MakeReportContract.Columns.PHARMACY_LOCATION,
                MakeReportContract.Columns.PHARMACIST_NAME,
                MakeReportContract.Columns.EMAIL,
                MakeReportContract.Columns.EMERGENCY_NOTE,
                MakeReportContract.Columns.SUBJECT,
                MakeReportContract.Columns.REPORT_DETAILS};
        // <order by> Tasks.SortOrder, Tasks.Name COLLATE NOCASE, We use "COLLATE NOCASE" for sorting order correctly
//        String category = MedicinesContract.Columns.MEDICINES_CATEGORY + "," + MedicinesContract.Columns.MEDICINES_NAME;
//        String name = MakeReportContract.Columns.PHARMACIST_NAME + " COLLATE NOCASE";

        switch(id) {
            case LOADER_ID:
                return new CursorLoader(getActivity(),
                        MakeReportContract.CONTENT_URI,
                        projection,
                        null,
                        null,
                        null);
            default:
                throw new InvalidParameterException(TAG + ".onCreateLoader called with invalid loader id" + id);
        }
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        Log.d(TAG, "onLoadFinished: starts");
        mAdapter.swapCursor(data);
        int count = mAdapter.getItemCount();
        Log.d(TAG, "onLoadFinished: " + count);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        Log.d(TAG, "onLoaderReset: starts");
        mAdapter.swapCursor(null);
    }
}
