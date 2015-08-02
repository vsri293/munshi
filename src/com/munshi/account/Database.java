package com.munshi.account;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database {
	private static final String DATABASE_NAME = "Account";
	private static final String DATABASE_TABLE = "Friend";
	private static final int DATABASE_VERSION = 1;
	
	public final static String Name = "name";
	public static final String Plus = "plus";
	public static final String Minus = "minus"; 
	public static final String Total = "tot"; 
	
	private DbHelper ourHelper; 
	private SQLiteDatabase ourDatabase;
	private final Context ourContext;
	
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null , DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
					Name + " TEXT PRIMARY KEY , " +
					Plus + " INTEGER NOT NULL, " + 
					Minus + " INTEGER NOT NULL, " + Total +" INTEGER NOT NULL);"
			);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int arg2) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
	}
	public Database(Context c)
	{
		ourContext = c;
	}
	
	public Database open() throws SQLException {
		ourHelper = new DbHelper(ourContext); 
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		ourHelper.close();
	}
	
	//update part completed
	public void updateEntry(String name, int plus,int minus,int total) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		
		String[] col = new String[]{Name,Plus,Minus,Total};
		
		Cursor c = ourDatabase.query(DATABASE_TABLE,col, Name + "=" + name, null, null, null, null);
		
		int iName = c.getColumnIndex(Name);
		int iPlus = c.getColumnIndex(Plus);
		int iMinus = c.getColumnIndex(Minus);
		int iTotal = c.getColumnIndex(Total);
		
		String str1 = c.getString(iTotal);
		String str2 = c.getString(iPlus);
		String str3 = c.getString(iMinus);
	    
		int i1 = Integer.parseInt(str1.trim());
	    int i2 = Integer.parseInt(str2.trim());
	    int i3 = Integer.parseInt(str3.trim());
	    
	    i1 = i1 + plus + minus;
	    i2 = i2 + plus;
	    i3 = i3 + minus;
	    
	    cv.put(Name,name);
		cv.put(Plus,i2);
		cv.put(Minus,i3);
		cv.put(Total,i1);
		
		ourDatabase.update(DATABASE_TABLE,cv,Name + "=" + name,null);
	}
	
	//create part completed
	public void createEntry(String name, int plus,int minus,int total) {
		ContentValues cv = new ContentValues();
		cv.put(Name,name);
		cv.put(Plus,plus);
		cv.put(Minus,minus);
		cv.put(Total,total);
		ourDatabase.insert(DATABASE_TABLE,null,cv);
	}
		
}
