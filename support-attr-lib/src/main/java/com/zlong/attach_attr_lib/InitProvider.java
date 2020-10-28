package com.zlong.attach_attr_lib;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/**
 * 初始化Provider
 * Time: 2020/9/23 0023
 * Author: zoulong
 */
public class InitProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        Application mApplication = (Application) getContext().getApplicationContext();
        AttachIns.get().init(mApplication);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
