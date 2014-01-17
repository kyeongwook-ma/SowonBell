package ma.dev.sowondejong.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ma.dev.sowondejong.data.SowonMessage;
import ma.dev.sowondejong.db.PhraseDB.PhraseDBScheme;
import ma.dev.sowondejong.db.SowonDB.SowonDBScheme;
import ma.dev.sowondejong.util.SowonDejonApp;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dev on 13. 8. 3.
 */

public class DBHelper extends SQLiteOpenHelper implements IDbCRUD {

	static final String KEY_COLUMN = "_id";
	private static final String DB_NAME = "SowonDB";
	private static DBHelper mInstance;

	private static SQLiteDatabase mDb;

	private DBHelper() {
		super(SowonDejonApp.getContext(), DB_NAME, null, 1);
	}

	public static DBHelper getInstance() {
		if (mInstance == null) {
			mInstance = new DBHelper();
			mDb = mInstance.getWritableDatabase();
		}
		return mInstance;
	}

	public void close() {
		if (mInstance != null) {
			mDb.close();
			mInstance = null;
		}
	}


	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		createDB(new PhraseDBCreator(), sqLiteDatabase);
		createDB(new SowonDBCreator(), sqLiteDatabase);
	}
	
	
	private void createDB(IDBCreator dbCreator, SQLiteDatabase db) {
		String[] tableCreateStmt = dbCreator.getCreateTableStmt();

		if (tableCreateStmt != null && tableCreateStmt.length > 0) {
			for (String stmt : tableCreateStmt) {
				db.execSQL(stmt);
			}
		}
	}


	

	@Override
	public Cursor getAllItem(String table, String[] columns) {
		return mDb.query(table, columns, null, null, null, null, null);
	}

	@Override
	public Cursor get(String table, String[] columns, long id) {
		Cursor cursor = mDb.query(true, table, columns, KEY_COLUMN + "=" + id, null, null, null, null, null);

		if (cursor != null)
			cursor.moveToFirst();

		return cursor;
	}

	public int getDBVersion() {
		return mDb.getVersion();
	}
	
	@Override
	public Cursor get(String sql) {
		return mDb.rawQuery(sql, null);
	}

	@Override
	public long insert(String table, ContentValues values) {
		return mDb.insert(table, null, values);	
	}

	public long replace(String table, String nullColumnHack, ContentValues values) {
		return mDb.replace(table, nullColumnHack, values);
	}
	
	@Override
	public int update(String table, ContentValues values, long id) {
		return mDb.update(table, values, KEY_COLUMN + "=" + id, null);
	}
	

	@Override
	public int update(String table, ContentValues values, String whereClause, String[] whereArgs) {
		return mDb.update(table, values, whereClause, whereArgs);
	}

	@Override
	public int delete(String table, String whereCaluse) {
		return mDb.delete(table, whereCaluse, null);
	}

	@Override
	public int delete(String table, long id) {
		return mDb.delete(table, KEY_COLUMN + "=?" , new String[] {String.valueOf(id)});
	}


	@Override
	public void exec(String sql) {
		mDb.execSQL(sql);
	}

	public List<String> getAllPhrase() {

		ArrayList<String> phraseList = new ArrayList<String>();

		Cursor cursor = getAllItem(PhraseDBScheme.TABLE_NAME, PhraseDBScheme.getColumnNames());

		if(cursor.moveToFirst()) {
			do {
				String phrase = cursor.getString(1);
				phraseList.add(phrase);
				
			} while(cursor.moveToNext());
		}

		return phraseList;
	}
	
	public List<SowonMessage> getAllSowonMessage() {
		
		ArrayList<SowonMessage> sowonList = new ArrayList<SowonMessage>();

		Cursor cursor = getAllItem(SowonDBScheme.TABLE_NAME, SowonDBScheme.getColumnNames());

		if(cursor.moveToFirst()) {
			do {
				int id = cursor.getInt(0);
				String from = cursor.getString(1);
				String to = cursor.getString(2);
				String date = cursor.getString(3);
				String type = cursor.getString(4);
				String message = cursor.getString(5);
				String imgPath = cursor.getString(6);
				
				
				SowonMessage msg = new SowonMessage(from, to, message, date, imgPath);
				msg.setType(type);
				msg.setId(id);
				sowonList.add(msg);

				
			} while(cursor.moveToNext());
		}

		Collections.reverse(sowonList);		
		return sowonList;
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
}
