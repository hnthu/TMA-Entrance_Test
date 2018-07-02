package services.impls;

import daos.KindDao;
import models.Kind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.KindService;

import java.util.List;

@Service
public class KindServiceImpl implements KindService {
    private KindDao kindDao;

    @Autowired
    public void setKindDao(KindDao kindDao) {
        this.kindDao = kindDao;
    }

    @Override
    @Transactional
    public Kind get(String name) {
        return kindDao.get(name);
    }

    @Override
    @Transactional
    public Kind getKindById(int id) {
        return kindDao.getKindById(id);
    }

    @Override
    @Transactional
    public List<Kind> search(String searchString) {
        return kindDao.search(searchString);
    }

    @Override
    @Transactional
    public List<Kind> getAll() {
        return kindDao.getAll();
    }

    @Override
    @Transactional
    public void add(Kind newQuestionType) {
        kindDao.add(newQuestionType);
    }

    @Override
    @Transactional
    public void update(Kind modifiedQuestionType) {
        kindDao.update(modifiedQuestionType);
    }

    @Override
    @Transactional
    public void delete(int id) {
        kindDao.delete(id);
    }
}
