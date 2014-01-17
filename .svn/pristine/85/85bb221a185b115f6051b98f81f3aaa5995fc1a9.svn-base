package ma.dev.sowondejong.db;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by Dev on 13. 8. 3.
 * @param <T>
 */
public interface IDbCRUD {

    Cursor getAllItem(String table, String[] columns);
    Cursor get(String table, String[] columns, long id);
    Cursor get(String sql);
 
    long insert(String table, ContentValues values);
    int update(String table, ContentValues values, long id);
    int delete(String table, String whereCaluse);
    int delete(String table, long id);
    void exec(String sql);
	int update(String table, ContentValues values, String whereClause,
			String[] whereArgs);

}
