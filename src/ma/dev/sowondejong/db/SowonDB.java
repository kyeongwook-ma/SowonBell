package ma.dev.sowondejong.db;

public class SowonDB {

	public static final class SowonDBScheme {

		public static final String TABLE_NAME = "SOWON_TABLE ";

		public static final String COLUMN_ID = DBHelper.KEY_COLUMN;
		public static final String COLUMN_FROM =  " MSG_FROM ";
		public static final String COLUMN_TO =  " MSG_TO ";
		public static final String COLUMN_DATE = " SOWON_DATE ";
		public static final String COLUMN_SEND_TYPE = " SEND_TYPE ";
		public static final String COLUMN_MESSAGE =  " MESSAGE ";
		public static final String COLUMN_IMG_PATH =  " IMG_PATH ";

		public static String[] getColumnNames() {
			return new String[]{ COLUMN_ID,  COLUMN_FROM, 
					COLUMN_TO, COLUMN_DATE, COLUMN_SEND_TYPE , COLUMN_MESSAGE, COLUMN_IMG_PATH};
		}

		public static String getColumnNamesInRow() {

			StringBuilder sb = new StringBuilder();

			for(String column : getColumnNames()) {
				sb.append(column + " , ");		
			}

			return sb.toString();
		}

	}
}
