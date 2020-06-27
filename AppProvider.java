package com.imran.familypharmacy;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Provider for the TaskTimer app. This is the only that knows about {@link AppDatabase}
 * This class is support for the content provider
 * This class make connection with the database
 * This class is responsible for link, create, query, insert, update and delete for every table in database
 */

public class AppProvider extends ContentProvider {
    private static final String TAG = "AppProvider";

    private AppDatabase mOpenHelper;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    static final String CONTENT_AUTHORITY = "com.imran.familypharmacy.provider";
    public static final Uri CONTENT_AUTHORITY_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private static final int MEDICINES = 100;
    private static final int MEDICINES_ID = 101;

    private static final int NOTIFICATIONS = 200;
    private static final int NOTIFICATIONS_ID = 201;

    private static final int REPORTS = 300;
    private static final int REPORTS_ID = 301;

    //This method build a uri for database
    //This way, we can access to the database table
    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        //  eg. content://com.imran.familypharmacy.provider/Medicines
        matcher.addURI(CONTENT_AUTHORITY, MedicinesContract.TABLE_NAME, MEDICINES);
        // e.g. content://com.imran.familypharmacy.provider/Medicines/8
        matcher.addURI(CONTENT_AUTHORITY, MedicinesContract.TABLE_NAME + "/#", MEDICINES_ID);

        //This Two line code for Timing table in Database for matching
        matcher.addURI(CONTENT_AUTHORITY, NotificationsContract.TABLE_NAME, NOTIFICATIONS);
        matcher.addURI(CONTENT_AUTHORITY, NotificationsContract.TABLE_NAME + "/#", NOTIFICATIONS_ID);

        //This Two line code for Reports table in Database for matching
        matcher.addURI(CONTENT_AUTHORITY, MakeReportContract.TABLE_NAME, REPORTS);
        matcher.addURI(CONTENT_AUTHORITY, MakeReportContract.TABLE_NAME + "/#", REPORTS_ID);

        //his Two line code for vwTaskDurations view in Database for matching
//        matcher.addURI(CONTENT_AUTHORITY, DurationsContract.TABLE_NAME, TASK_DURATIONS);
//        matcher.addURI(CONTENT_AUTHORITY, DurationsContract.TABLE_NAME + "/#", TASK_DURATIONS_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = AppDatabase.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.d(TAG, "query: called with URI " + uri);
        final int match = sUriMatcher.match(uri);
        Log.d(TAG, "query: match is " + match);

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        //Here we tell the queryBuilder to which table we need to query
        switch(match) {
            //Case MEDICINES and MEDICINES_ID for query from MEDICINES Table
            case MEDICINES:
                //Here we set the table
                queryBuilder.setTables(MedicinesContract.TABLE_NAME);
                break;

            case MEDICINES_ID:
                queryBuilder.setTables(MedicinesContract.TABLE_NAME);
                //This getMedicineId come from MedicinesContract class
                long medicineId = MedicinesContract.getMedicineId(uri);
                //here we matching the ID
                queryBuilder.appendWhere(MedicinesContract.Columns._ID + " = " + medicineId);
                break;

            //Case NOTIFICATIONS_ID and NOTIFICATIONS for query from Notifications Table
            case NOTIFICATIONS:
                queryBuilder.setTables(NotificationsContract.TABLE_NAME);
                break;

            case NOTIFICATIONS_ID:
                queryBuilder.setTables(NotificationsContract.TABLE_NAME);
                long notificationId = NotificationsContract.getNotificationId(uri);
                queryBuilder.appendWhere(NotificationsContract.Columns._ID + " = " + notificationId);
                break;

            //Case REPORTS and REPORTS_ID for query from Notifications Table
            case REPORTS:
                queryBuilder.setTables(MakeReportContract.TABLE_NAME);
                break;

            case REPORTS_ID:
                queryBuilder.setTables(MakeReportContract.TABLE_NAME);
                long reportId = MakeReportContract.getReportId(uri);
                queryBuilder.appendWhere(MakeReportContract.Columns._ID + " = " + reportId);
                break;


            //case TASK_DURATIONS and case TASK_DURATIONS_ID for query for vwTaskDurations view
//            case TASK_DURATIONS:
//                queryBuilder.setTables(DurationsContract.TABLE_NAME);
//                break;
//
//            case TASK_DURATIONS_ID:
//                queryBuilder.setTables(DurationsContract.TABLE_NAME);
//                long durationId = DurationsContract.getDurationId(uri);
//                queryBuilder.appendWhere(DurationsContract.Columns._ID + " = " + durationId);
//                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);

        }

        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
//        return queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        Log.d(TAG, "query: rows in return cursor " + cursor.getCount()); // remove this line

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            //This MEDICINES and MEDICINES_ID is for MEDICINES table
            case MEDICINES:
                return MedicinesContract.CONTENT_TYPE;

            case MEDICINES_ID:
                return MedicinesContract.CONTENT_ITEM_TYPE;

            //This NOTIFICATIONS and NOTIFICATIONS_ID is for TIMINGS table
            case NOTIFICATIONS:
                return NotificationsContract.CONTENT_TYPE;

            case NOTIFICATIONS_ID:
                return NotificationsContract.CONTENT_ITEM_TYPE;

            //This REPORTS and REPORTS_ID is for Reports table
            case REPORTS:
                return MakeReportContract.CONTENT_TYPE;

            case REPORTS_ID:
                return MakeReportContract.CONTENT_ITEM_TYPE;

            //This TASK_DURATIONS_ID and TASK_DURATIONS is for vwTaskDurations view
//            case TASK_DURATIONS:
//                return DurationsContract.CONTENT_TYPE;
//
//            case TASK_DURATIONS_ID:
//                return DurationsContract.CONTENT_ITEM_TYPE;

            default:
                throw new IllegalArgumentException("unknown Uri: " + uri);
        }
    }

    //This method is for insert record into table in database
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        Log.d(TAG, "Entering insert, called with uri:" + uri);
        final int match = sUriMatcher.match(uri);
        Log.d(TAG, "match is " + match);

        final SQLiteDatabase db;

        Uri returnUri;
        long recordId;

        switch(match) {
            //This Case MEDICINES responsible for insert data into MEDICINES table
            case MEDICINES:
                db = mOpenHelper.getWritableDatabase();
                recordId = db.insert(MedicinesContract.TABLE_NAME, null, values);
                if(recordId >=0) {
                    returnUri = MedicinesContract.buildMedicineUri(recordId);
                } else {
                    throw new android.database.SQLException("Failed to insert into " + uri.toString());
                }
                break;

            //This Case NOTIFICATIONS responsible for insert data into Timing table
            case NOTIFICATIONS:
                db = mOpenHelper.getWritableDatabase();
                recordId = db.insert(NotificationsContract.TABLE_NAME, null, values);
                if(recordId >=0) {
                    returnUri = NotificationsContract.buildNotificationUri(recordId);
                } else {
                    throw new android.database.SQLException("Failed to insert into " + uri.toString());
                }
                break;

            //This Case NOTIFICATIONS responsible for insert data into Timing table
            case REPORTS:
                db = mOpenHelper.getWritableDatabase();
                recordId = db.insert(MakeReportContract.TABLE_NAME, null, values);
                if(recordId >=0) {
                    returnUri = MakeReportContract.buildReportUri(recordId);
                } else {
                    throw new android.database.SQLException("Failed to insert into " + uri.toString());
                }
                break;

            default:
                throw new IllegalArgumentException("Unknown uri: " + uri);
        }

//        This code will update app automatically after adding any record in database.
        if(recordId >= 0){
            //Somethings was inserted
            Log.d(TAG, "insert: Setting notify changed with " + uri);
            getContext().getContentResolver().notifyChange(uri, null);
        }else {
            Log.d(TAG, "insert: Nothing inserted");
        }

        Log.d(TAG, "Exiting insert, returning " + returnUri);
        return returnUri;

    }

    //This method is for delete record into table in database
    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        Log.d(TAG, "delete called with uri " + uri);
        final int match = sUriMatcher.match(uri);
        Log.d(TAG, "match is " + match);

        final SQLiteDatabase db;
        int count;

        String selectionCriteria;

        switch(match) {
            //This Case MEDICINES and MEDICINES_ID responsible for delete data from MEDICINES table
            case MEDICINES:
                db = mOpenHelper.getWritableDatabase();
                count = db.delete(MedicinesContract.TABLE_NAME, selection, selectionArgs);
                break;

            case MEDICINES_ID:
                db = mOpenHelper.getWritableDatabase();
                long medicineId = MedicinesContract.getMedicineId(uri);
                selectionCriteria = MedicinesContract.Columns._ID + " = " + medicineId;

                if((selection != null) && (selection.length()>0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.delete(MedicinesContract.TABLE_NAME, selectionCriteria, selectionArgs);
                break;

            //This Case Timings and Timings_ID responsible for delete data from Timing table
            case NOTIFICATIONS:
                db = mOpenHelper.getWritableDatabase();
                count = db.delete(NotificationsContract.TABLE_NAME, selection, selectionArgs);
                break;

            case NOTIFICATIONS_ID:
                db = mOpenHelper.getWritableDatabase();
                long notificationId = NotificationsContract.getNotificationId(uri);
                selectionCriteria = NotificationsContract.Columns._ID + " = " + notificationId;

                if((selection != null) && (selection.length()>0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.delete(NotificationsContract.TABLE_NAME, selectionCriteria, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown uri: " + uri);
        }

        //This code will update app automatically after deleting any record in database.
        if(count > 0){
            //Something was deleted
            Log.d(TAG, "delete: Setting notify changed with " + uri);
            getContext().getContentResolver().notifyChange(uri, null);
        }else {
            Log.d(TAG, "delete: Nothing Deleted");
        }

        Log.d(TAG, "Exiting update, returning " + count);
        return count;
    }

    //This method is for update record into table in database
    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.d(TAG, "update called with uri " + uri);
        final int match = sUriMatcher.match(uri);
        Log.d(TAG, "match is " + match);

        final SQLiteDatabase db;
        int count;

        String selectionCriteria;

        switch(match) {
            //This Case MEDICINES and MEDICINES_ID responsible for update data in MEDICINES table
            case MEDICINES:
                db = mOpenHelper.getWritableDatabase();
                count = db.update(MedicinesContract.TABLE_NAME, values, selection, selectionArgs);
                break;

            case MEDICINES_ID:
                db = mOpenHelper.getWritableDatabase();
                long medicineId = MedicinesContract.getMedicineId(uri);
                selectionCriteria = MedicinesContract.Columns._ID + " = " + medicineId;

                if((selection != null) && (selection.length()>0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.update(MedicinesContract.TABLE_NAME, values, selectionCriteria, selectionArgs);
                break;

            //This Case TIMINGS and TIMINGS_ID responsible for update data in TIMINGS table
//            case TIMINGS:
//                db = mOpenHelper.getWritableDatabase();
//                count = db.update(TimingsContract.TABLE_NAME, values, selection, selectionArgs);
//                break;
//
//            case TIMINGS_ID:
//                db = mOpenHelper.getWritableDatabase();
//                long timingsId = TimingsContract.getTimingId(uri);
//                selectionCriteria = TimingsContract.Columns._ID + " = " + timingsId;
//
//                if((selection != null) && (selection.length()>0)) {
//                    selectionCriteria += " AND (" + selection + ")";
//                }
//                count = db.update(TimingsContract.TABLE_NAME, values, selectionCriteria, selectionArgs);
//                break;

            default:
                throw new IllegalArgumentException("Unknown uri: " + uri);
        }

        //This code will update app automatically after updating any record in database.
        if(count > 0){
            //Something was deleted
            Log.d(TAG, "update: Setting notify changed with " + uri);
            getContext().getContentResolver().notifyChange(uri, null);
        }else {
            Log.d(TAG, "update: Nothing updated");
        }

        Log.d(TAG, "Exiting update, returning " + count);
        return count;
    }
}
