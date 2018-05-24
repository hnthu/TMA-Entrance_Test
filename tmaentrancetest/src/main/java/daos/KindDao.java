package daos;

import models.Kind;
import java.util.List;

public interface KindDao {
    public Kind get(String questionType);
    public Kind getKindById(int id);
    public List<Kind> search(String searchString);
    public List<Kind> getAll();
    public void add(Kind newQuestionType);
    public void update(Kind modifiedQuestionType);
    public void delete(int id);
}
