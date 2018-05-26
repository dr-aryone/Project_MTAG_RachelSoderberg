package com.mtag.app.muaythaiathletesguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;

public class PortalRepo {
    private PortalSQLiteHelper portalSQLiteHelper;

    public PortalRepo(Context context) {
        portalSQLiteHelper = new PortalSQLiteHelper(context);
    }

    public int insert(Portal portal) {
        SQLiteDatabase db = portalSQLiteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Portal.KEY_IN_DESC, portal.inDesc);
        values.put(Portal.KEY_IN_NAME, portal.inName);

        long portal_id = db.insert(Portal.TABLE_PORTAL, null, values);
        db.close();
        return (int) portal_id;
    }

    public void delete(int portal_id) {
        SQLiteDatabase db = portalSQLiteHelper.getWritableDatabase();
        db.delete(Portal.TABLE_PORTAL, Portal.KEY_ID + "= ?", new String[] { String.valueOf(portal_id) });
        db.close();
    }

    public void update(Portal portal) {
        SQLiteDatabase db = portalSQLiteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Portal.KEY_IN_DESC, portal.inDesc);
        values.put(Portal.KEY_IN_NAME, portal.inName);

        db.update(Portal.TABLE_PORTAL, values, Portal.KEY_ID + "= ?", new String[] { String.valueOf(portal.id) });
        db.close();
    }

    public ArrayList<HashMap<String, String>> getPortalList() {
        SQLiteDatabase db = portalSQLiteHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Portal.KEY_ID + "," +
                Portal.KEY_IN_NAME + "," +
                Portal.KEY_IN_DESC +
                " FROM " + Portal.TABLE_PORTAL;

        ArrayList<HashMap<String, String>> portalList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> portal = new HashMap<String, String>();
                portal.put("portal_id", cursor.getString(cursor.getColumnIndex(Portal.KEY_ID)));
                portal.put("inName", cursor.getString(cursor.getColumnIndex(Portal.KEY_IN_NAME)));
                portal.put("inDesc", cursor.getString(cursor.getColumnIndex(Portal.KEY_IN_DESC)));
                portalList.add(portal);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return portalList;
    }

    public Portal getPortalById(int Id){
        SQLiteDatabase db = portalSQLiteHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Portal.KEY_ID + "," +
                Portal.KEY_IN_NAME + "," +
                Portal.KEY_IN_DESC +
                " FROM " + Portal.TABLE_PORTAL
                + " WHERE " +
                Portal.KEY_ID + "=?";

        Portal portal = new Portal();
        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                portal.id =cursor.getInt(cursor.getColumnIndex(Portal.KEY_ID));
                portal.inName =cursor.getString(cursor.getColumnIndex(Portal.KEY_IN_NAME));
                portal.inDesc  =cursor.getString(cursor.getColumnIndex(Portal.KEY_IN_DESC));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return portal;
    }
}

