package com.example.ch12_4insertrecexercise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CompDBHper extends SQLiteOpenHelper {
//	private static final String DB_NAME = "Company";
	private static final String TABLE_NAME = "Cus";
	private static final String CREATE_TABLE = 
		"CREATE TABLE " + TABLE_NAME + 
		" (" + 
		" cusNo VARCHAR(10) NOT NULL, " + 
		" cusNa VARCHAR(20) NOT NULL, " + 
		" cusPho VARCHAR(20), " +
		" cusAdd VARCHAR(50), PRIMARY KEY(cusNo) );";
			 
	public CompDBHper(Context context, String DBname, 
			CursorFactory factory, int DBversion) {
		//factory:null 表採用預設值
		super(context, "Company", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
	
	public long insertRec(String CusNo, String CusNa, String CusPho, 
		String CusAdd){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues rec = new ContentValues();
		rec.put("cusNo", CusNo);
		rec.put("cusNa", CusNa);
		rec.put("cusPho", CusPho);
		rec.put("cusAdd", CusAdd);
		long rowID = db.insert(TABLE_NAME, null, rec);
		return rowID;
		
	}
	
	public int RecCount(){
		SQLiteDatabase db = getWritableDatabase();
		String sql = "SELECT * FROM " + TABLE_NAME;
		Cursor recSet = db.rawQuery(sql, null);
		return recSet.getCount();
		
	}
}