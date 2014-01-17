package ma.dev.sowondejong.db;

/**
 * Created by Dev on 13. 8. 3.
 */
public interface IDBCreator {
    String[] getCreateTableStmt();
    String[] getInitDataStmt();
}
