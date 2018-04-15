package daos;
import models.Category;
import java.util.List;

public interface CategoryDao {
    public Category get(String categoryName);
    public Category getCategoryById(int id);
    public List<Category> search(String searchString);
    public List<Category> getAll();
    public void add(Category newCategory);
    public void update(Category modifiedCategory);
    public void delete(int id);
}
