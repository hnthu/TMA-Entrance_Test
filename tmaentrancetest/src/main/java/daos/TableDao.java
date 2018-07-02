package daos;

public interface TableDao {
    public void deleteAllRecord(String tableName, int[] selectedIds);
}
