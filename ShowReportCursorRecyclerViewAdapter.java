package com.imran.familypharmacy;

import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class ShowReportCursorRecyclerViewAdapter extends
        RecyclerView.Adapter<ShowReportCursorRecyclerViewAdapter.AllReportsViewHolder> {
    private static final String TAG = "CursorRecyclerViewAdapt";
    private Cursor mCursor;



    public ShowReportCursorRecyclerViewAdapter(Cursor cursor) {
        this.mCursor = cursor;
    }

    @NonNull
    @Override
    public AllReportsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_show_reports, parent, false);
        return new AllReportsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AllReportsViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: starts");
        if ((mCursor == null) || (mCursor.getCount() == 0)) {

        }else {
            if (!mCursor.moveToPosition(position)) {
                throw new IllegalStateException("Could not move cursor to position " + position);
            }

            holder.pharmacy_ID.setText(mCursor.getString(mCursor.getColumnIndex(MakeReportContract.Columns.PHARMACY_ID)));
            holder.pharmacy_Location.setText(" - " + mCursor.getString(mCursor.getColumnIndex(MakeReportContract.Columns.PHARMACY_LOCATION)));
            holder.pharmacist_Name.setText("Pharmacist Name: " + mCursor.getString(mCursor.getColumnIndex(MakeReportContract.Columns.PHARMACIST_NAME)));
            holder.email.setText("Email: " + mCursor.getString(mCursor.getColumnIndex(MakeReportContract.Columns.EMAIL)));
            holder.emergency_Note.setText("Note: " + mCursor.getString(mCursor.getColumnIndex(MakeReportContract.Columns.EMERGENCY_NOTE)));
            holder.subject.setText("Subject: " + mCursor.getString(mCursor.getColumnIndex(MakeReportContract.Columns.SUBJECT)));
            holder.reports_details.setText(mCursor.getString(mCursor.getColumnIndex(MakeReportContract.Columns.REPORT_DETAILS)));
        }

    }

    @Override
    public int getItemCount() {
        if ((mCursor == null) || (mCursor.getCount() == 0)) {
            return 1;
        } else {
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
        if (newCursor != null) {
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
    static class AllReportsViewHolder extends RecyclerView.ViewHolder {
        TextView pharmacy_ID = null;
        TextView pharmacy_Location = null;
        TextView pharmacist_Name = null;
        TextView email = null;
        TextView emergency_Note = null;
        TextView subject = null;
        TextView reports_details = null;


        private AllReportsViewHolder(View itemView) {
            super(itemView);
            this.pharmacy_ID = itemView.findViewById(R.id.show_r_pharmacyID);
            this.pharmacy_Location = itemView.findViewById(R.id.show_r_pharmacyLocation);
            this.pharmacist_Name = itemView.findViewById(R.id.show_r_pharmacistName);
            this.email = itemView.findViewById(R.id.show_r_email);
            this.emergency_Note = itemView.findViewById(R.id.show_r_emergencyNote);
            this.subject = itemView.findViewById(R.id.show_r_subject);
            this.reports_details = itemView.findViewById(R.id.show_r_reportsDetails);
        }
    }
}
