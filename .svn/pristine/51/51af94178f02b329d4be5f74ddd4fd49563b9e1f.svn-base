package ma.dev.sowondejong.db;

import ma.dev.sowondejong.db.SowonDB.SowonDBScheme;

public class SowonDBCreator implements IDBCreator {

	@Override
	public String[] getCreateTableStmt() {
		/*
        Create table : DDL
		 */
		final String SOWON_TABLE_CREATE_STMT = "CREATE TABLE "
				+ SowonDBScheme.TABLE_NAME + " ("
				+ SowonDBScheme.COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
				+ SowonDBScheme.COLUMN_FROM + " TEXT, "
				+ SowonDBScheme.COLUMN_TO + " TEXT, "
				+ SowonDBScheme.COLUMN_DATE + " TEXT, "
				+ SowonDBScheme.COLUMN_SEND_TYPE + " TEXT, "
				+ SowonDBScheme.COLUMN_MESSAGE + " TEXT, "
				+ SowonDBScheme.COLUMN_IMG_PATH + " TEXT " + ");";

		return new String[]{ SOWON_TABLE_CREATE_STMT };
	}

	@Override
	public String[] getInitDataStmt() {
		return new String[0];
	}
}
