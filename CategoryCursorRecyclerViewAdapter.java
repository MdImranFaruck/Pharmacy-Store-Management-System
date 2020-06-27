package com.imran.familypharmacy;

import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class CategoryCursorRecyclerViewAdapter extends RecyclerView.Adapter<CategoryCursorRecyclerViewAdapter.MedicineViewHolder> {
    private static final String TAG = "CursorRecyclerViewAdapt";
    private Cursor mCursor;


    public CategoryCursorRecyclerViewAdapter(Cursor cursor) {
        Log.d(TAG, "CategoryCursorRecyclerViewAdapter: constructor called");
        this.mCursor = cursor;
    }

    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: new view requested");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_list_item, parent, false);
        return new MedicineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MedicineViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: starts");
        if ((mCursor == null) || (mCursor.getCount() == 0)) {

        }else {
            if (!mCursor.moveToPosition(position)) {
                throw new IllegalStateException("Could not move cursor to position " + position);
            }

            holder.name.setText(mCursor.getString(mCursor.getColumnIndex(MedicinesContract.Columns.MEDICINES_NAME)));
            holder.company.setText(mCursor.getString(mCursor.getColumnIndex(MedicinesContract.Columns.MEDICINES_COMPANY)));
            holder.location.setText(mCursor.getString(mCursor.getColumnIndex(MedicinesContract.Columns.MEDICINES_LOCATION)));
            holder.price.setText("$" + mCursor.getString(mCursor.getColumnIndex(MedicinesContract.Columns.MEDICINES_PRICE)));
        }

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: starts");
        if((mCursor == null) || (mCursor.getCount() == 0)){
            return 1;
        }else {
            return mCursor.getCount();
        }
    }

    /**
     * Swap in a new Cursor, returning the old Cursor.
     * The returned old Cursor is <em>not</em> closed.
     *
     * @param newCursor The new cursor to be used
     * @return Returns the previously set Cursor, or null if there wasn't one.
     * If the given new Cursor is the same instance as the previously set
     * Cursor, null is also returned.
     */
    Cursor swapCursor(Cursor newCursor) {
        if (newCursor == mCursor) {
            return null;
        }

        final Cursor oldCursor = mCursor;
        mCursor = newCursor;
        if(newCursor != null) {
            // notify the observers about the new cursor
            notifyDataSetChanged();
        } else {
            // notify the observers about the lack of a data set
            notifyItemRangeRemoved(0, getItemCount());
        }
        return oldCursor;

    }

    //This class will be created for every row in recycler view
    //The class will show every every medicine in category layout
    static class MedicineViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "MedicineViewHolder";

        TextView name = null;
        TextView company = null;
        TextView location = null;
        TextView price = null;


        public MedicineViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "MedicineViewHolder: starts");

            this.name = itemView.findViewById(R.id.med_name);
            this.company = itemView.findViewById(R.id.med_company);
            this.location = itemView.findViewById(R.id.med_location);
            this.price = itemView.findViewById(R.id.med_price);
        }
    }

}
