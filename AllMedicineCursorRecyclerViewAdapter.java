package com.imran.familypharmacy;

import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class AllMedicineCursorRecyclerViewAdapter extends RecyclerView.Adapter<AllMedicineCursorRecyclerViewAdapter.AllMedicineViewHolder> {
    private static final String TAG = "CursorRecyclerViewAdapt";

    private Cursor mCursor;
    private onMedicineClickListener mListener;

    interface onMedicineClickListener{
        void onEditClick(Medicine medicine);
        void onDeleteClick(Medicine medicine);
    }


    public AllMedicineCursorRecyclerViewAdapter(Cursor cursor, onMedicineClickListener listener) {
        Log.d(TAG, "AllMedicineCursorRecyclerViewAdapter: constructor called");
        mCursor = cursor;
        mListener = listener;
    }

    @NonNull
    @Override
    public AllMedicineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "AllMedicineViewHolder: new view requested");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_medicine_list, parent, false);
        return new AllMedicineViewHolder(view);
    }

    @Override
        public void onBindViewHolder(AllMedicineViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: starts");
        if ((mCursor == null) || (mCursor.getCount() == 0)) {

        }else {
            if (!mCursor.moveToPosition(position)) {
                throw new IllegalStateException("Could not move cursor to position " + position);
            }

            final Medicine medicine = new Medicine(mCursor.getLong(mCursor.getColumnIndex(MedicinesContract.Columns._ID)),
                    mCursor.getString(mCursor.getColumnIndex(MedicinesContract.Columns.MEDICINES_NAME)),
                    mCursor.getString(mCursor.getColumnIndex(MedicinesContract.Columns.MEDICINES_COMPANY)),
                    mCursor.getString(mCursor.getColumnIndex(MedicinesContract.Columns.MEDICINES_LOCATION)),
                    mCursor.getString(mCursor.getColumnIndex(MedicinesContract.Columns.MEDICINES_PRICE)),
                    mCursor.getInt(mCursor.getColumnIndex(MedicinesContract.Columns.MEDICINES_CATEGORY)));

            //All this field going to show in layout
            holder.allName.setText(mCursor.getString(mCursor.getColumnIndex(MedicinesContract.Columns.MEDICINES_NAME)));
            holder.allLocation.setText("Location: " + mCursor.getString(mCursor.getColumnIndex(MedicinesContract.Columns.MEDICINES_LOCATION)));
            holder.allPrice.setText("Price: $" + mCursor.getString(mCursor.getColumnIndex(MedicinesContract.Columns.MEDICINES_PRICE)));
            holder.allCompany.setText("Company: " + mCursor.getString(mCursor.getColumnIndex(MedicinesContract.Columns.MEDICINES_COMPANY)));
            holder.editButton.setVisibility(View.VISIBLE);
            holder.deleteButton.setVisibility(View.VISIBLE);

            //This onClickListener for edit and delete button
            View.OnClickListener buttonListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: starts");
                    switch (view.getId()){
                        case R.id.all_med_edit:
                            if(mListener != null){
                                mListener.onEditClick(medicine);
                            }
                            break;
                        case R.id.all_med_delete:
                            if(mListener != null){
                                mListener.onDeleteClick(medicine);
                            }
                            break;
                            default:
                                Log.d(TAG, "onClick: found unexpected button id");
                    }
                    Log.d(TAG, "onClick: button with id " + view.getId() + " clicked.");
                    Log.d(TAG, "onClick: task name is " + medicine.getName());
                }
            };

            holder.editButton.setOnClickListener(buttonListener);
            holder.deleteButton.setOnClickListener(buttonListener);
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
    static class AllMedicineViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "AllMedicineViewHolder";

        TextView allName = null;
        TextView allLocation = null;
        TextView allPrice = null;
        TextView allCompany = null;
        ImageButton editButton = null;
        ImageButton deleteButton = null;


        private AllMedicineViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "AllMedicineViewHolder: starts");

            this.allName = itemView.findViewById(R.id.all_med_name);
            this.allLocation = itemView.findViewById(R.id.all_med_location);
            this.allPrice = itemView.findViewById(R.id.all_med_price);
            this.allCompany = itemView.findViewById(R.id.all_med_company);
            this.editButton = itemView.findViewById(R.id.all_med_edit);
            this.deleteButton = itemView.findViewById(R.id.all_med_delete);
        }
    }

}
