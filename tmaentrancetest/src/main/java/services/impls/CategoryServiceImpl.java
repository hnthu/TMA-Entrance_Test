package services.impls;
import daos.CategoryDao;
import models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.CategoryService;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    private CategoryDao categoryDao;

    @Autowired
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    @Transactional
    public Category get(String name) {
        return categoryDao.get(name);
    }

    @Override
    @Transactional
    public Category getCategoryById(int id) {
        return categoryDao.getCategoryById(id);
    }

    @Override
    @Transactional
    public List<Category> search(String searchString) {
        return categoryDao.search(searchString);
    }

    @Override
    @Transactional
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    @Override
    @Transactional
    public void add(Category newCategory) {
        categoryDao.add(newCategory);
    }

    @Override
    @Transactional
    public void update(Category modifiedCategory) {
        categoryDao.update(modifiedCategory);
    }

    @Override
    @Transactional
    public void delete(int id) {
        categoryDao.delete(id);
    }
}
