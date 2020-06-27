package com.imran.familypharmacy;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import static com.imran.familypharmacy.AppProvider.CONTENT_AUTHORITY;
import static com.imran.familypharmacy.AppProvider.CONTENT_AUTHORITY_URI;

public class MedicinesContract {

    static final String TABLE_NAME = "Medicines";

    // Medicines fields
    public static class Columns {
        public static final String _ID = BaseColumns._ID;
        public static final String MEDICINES_NAME = "MedicineName";
        public static final String MEDICINES_COMPANY = "Company";
        public static final String MEDICINES_PRICE = "Price";
        public static final String MEDICINES_LOCATION = "Location";
        public static final String MEDICINES_CATEGORY = "Category";

        private Columns() {
            // private constructor to prevent instantiation
        }
    }

    /**
     * The URI to access the Tasks table
     */
    public static final Uri CONTENT_URI = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_NAME);

    static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;
    static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;

    //We make public field because we grant access other app to use this database table
    public static Uri buildMedicineUri(long medicineId) {
        return ContentUris.withAppendedId(CONTENT_URI, medicineId);
    }

    //We make public field because we grant access other app to use this database table
    public static long getMedicineId(Uri uri) {
        return ContentUris.parseId(uri);
    }

}
