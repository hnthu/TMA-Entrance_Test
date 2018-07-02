package daos.impls;
import com.google.gson.Gson;
import daos.CategoryDao;
import models.Category;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTransactionManager manager;

    @Override
    public Category get(String categoryName) {
        return this.manager.getSessionFactory().getCurrentSession().get(Category.class, categoryName);
    }

    public Category getCategoryById(int id) {
        return this.manager.getSessionFactory().getCurrentSession().get(Category.class, id);
    }

    @Override
    public List<Category> search(String searchString) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Category> getAll() {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        List<Category> categoryList = session.createQuery("from Category").list();
        return categoryList;
    }

    @Override
    public void add(Category newCategory) {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        session.save(newCategory);
        logger.info("Category saved successfully, Category Details=" + newCategory);
    }

    @Override
    public void update(Category modifiedUser) {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        session.update(modifiedUser);
        logger.info("Category updated successfully, Category Details=" + modifiedUser);
    }

    @Override
    public void delete(int id) {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        Category p = (Category) session.get(Category.class, id);
        if (null != p) {
            session.delete(p);
        }
        logger.info("Category deleted successfully, Category details=" + p);
    }
}
