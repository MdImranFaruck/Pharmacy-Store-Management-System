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

class AllNotificationCursorRecyclerViewAdapter extends
        RecyclerView.Adapter<AllNotificationCursorRecyclerViewAdapter.AllNotificationViewHolder> {
    private static final String TAG = "CursorRecyclerViewAdapt";
    private Cursor mCursor;



    public AllNotificationCursorRecyclerViewAdapter(Cursor cursor) {
        this.mCursor = cursor;
    }

    @NonNull
    @Override
    public AllNotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_notifications_list, parent, false);
        return new AllNotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AllNotificationViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: starts");
        if ((mCursor == null) || (mCursor.getCount() == 0)) {

        }else {
            if (!mCursor.moveToPosition(position)) {
                throw new IllegalStateException("Could not move cursor to position " + position);
            }

            holder.SenderName.setText(mCursor.getString(mCursor.getColumnIndex(NotificationsContract.Columns.NOTIFICATIONS_SENDER_NAME)));
            holder.Subject.setText(mCursor.getString(mCursor.getColumnIndex(NotificationsContract.Columns.NOTIFICATIONS_SUBJECT)));
            holder.Content.setText(mCursor.getString(mCursor.getColumnIndex(NotificationsContract.Columns.NOTIFICATIONS_CONTENT)));
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
    static class AllNotificationViewHolder extends RecyclerView.ViewHolder {
        TextView SenderName = null;
        TextView Subject = null;
        TextView Content = null;
        ImageButton replyButton = null;
        ImageButton deleteButton = null;


        private AllNotificationViewHolder(View itemView) {
            super(itemView);
            this.SenderName = itemView.findViewById(R.id.all_not_name);
            this.Subject = itemView.findViewById(R.id.all_not_subject);
            this.Content = itemView.findViewById(R.id.all_not_content);
            this.replyButton = itemView.findViewById(R.id.all_not_reply);
            this.deleteButton = itemView.findViewById(R.id.all_not_delete);
        }
    }
}