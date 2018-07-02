package services.impls;

import daos.TableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.TableService;

@Service
public class TableServiceImpl implements TableService{

    private TableDao tableDao;

    @Autowired
    public void setTableDao(TableDao tableDao) {
        this.tableDao = tableDao;
    }

    @Override
    @Transactional
    public void deleteAllRecord(String tableName, int[] selectedIds){
        this.tableDao.deleteAllRecord(tableName, selectedIds);
    }
}
