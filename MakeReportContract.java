package com.imran.familypharmacy;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import static com.imran.familypharmacy.AppProvider.CONTENT_AUTHORITY;
import static com.imran.familypharmacy.AppProvider.CONTENT_AUTHORITY_URI;

public class MakeReportContract {

    static final String TABLE_NAME = "Reports";

    // Medicines fields
    public static class Columns {
        public static final String _ID = BaseColumns._ID;
        public static final String PHARMACY_ID = "Pharmacy_ID";
        public static final String PHARMACY_LOCATION = "Pharmacy_Location";
        public static final String PHARMACIST_NAME = "Pharmacist_Name";
        public static final String EMAIL = "Email";
        public static final String EMERGENCY_NOTE = "Emergency_Note";
        public static final String SUBJECT = "Subject";
        public static final String REPORT_DETAILS = "Report_Details";

        private Columns() {
            // private constructor to prevent instantiation
        }
    }

    /**
     * The URI to access the report table
     */
    public static final Uri CONTENT_URI = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_NAME);

    static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;
    static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;

    //We make public field because we grant access other app to use this database table
    public static Uri buildReportUri(long reportId) {
        return ContentUris.withAppendedId(CONTENT_URI, reportId);
    }

    //We make public field because we grant access other app to use this database table
    public static long getReportId(Uri uri) {
        return ContentUris.parseId(uri);
    }
}
