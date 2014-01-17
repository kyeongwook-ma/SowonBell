package ma.dev.sowondejong.db;

import ma.dev.sowondejong.db.PhraseDB.PhraseDBScheme;

/**
 * Created by Dev on 13. 8. 3.
 */
public class PhraseDBCreator implements IDBCreator {


	@Override
	public String[] getCreateTableStmt() {
		/*
        Create table : DDL
		 */
		final String PHRASE_TABLE_CREATE_STMT = "CREATE TABLE "
				+ PhraseDBScheme.TABLE_NAME + " ("
				+ PhraseDBScheme.COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
				+ PhraseDBScheme.COLUMN_PHRASE + " TEXT " + ");";

		return new String[]{ PHRASE_TABLE_CREATE_STMT };
	}

	@Override
	public String[] getInitDataStmt() {
		return new String[0];
	}
}
