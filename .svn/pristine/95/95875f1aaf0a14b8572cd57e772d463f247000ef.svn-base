package ma.dev.sowondejong.db;

/**
 * Created by Dev on 13. 8. 1.
 */
public class PhraseDB {

	/**
	 * @author
	 */
	public static final class PhraseDBScheme {

		private PhraseDBScheme() {
		}

		public static final String TABLE_NAME = "PHRASE_TABLE ";

		public static final String COLUMN_ID = DBHelper.KEY_COLUMN;
		public static final String COLUMN_PHRASE =  " PHRASE ";
	

		public static String[] getColumnNames() {
			return new String[]{ COLUMN_ID, COLUMN_PHRASE };
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
