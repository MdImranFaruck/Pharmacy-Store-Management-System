package com.imran.familypharmacy;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import static com.imran.familypharmacy.AppProvider.CONTENT_AUTHORITY;
import static com.imran.familypharmacy.AppProvider.CONTENT_AUTHORITY_URI;

public class NotificationsContract {

    static final String TABLE_NAME = "Notifications";

    // Notifications fields
    public static class Columns {
        //CREATE TABLE Notifications (_ID INTEGER PRIMARY KEY NOT NULL, SenderName TEXT NOT NULL, Subject TEXT, Content TEXT NOT NULL, PharmacyID INTEGER NOT NULL);
        public static final String _ID = BaseColumns._ID;
        public static final String NOTIFICATIONS_SENDER_NAME = "SenderName";
        public static final String NOTIFICATIONS_SUBJECT = "Subject";
        public static final String NOTIFICATIONS_CONTENT = "Content";
        public static final String NOTIFICATIONS_PHARMACY_ID = "PharmacyID";

        private Columns() {
            // private constructor to prevent instantiation
        }
    }

    /**
     * The URI to access the Notifications table
     */
    public static final Uri CONTENT_URI = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_NAME);

    static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;
    static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;

    //We make public field because we grant access other app to use this database table
    public static Uri buildNotificationUri(long notificationId) {
        return ContentUris.withAppendedId(CONTENT_URI, notificationId);
    }

    //We make public field because we grant access other app to use this database table
    public static long getNotificationId(Uri uri) {
        return ContentUris.parseId(uri);
    }
}
