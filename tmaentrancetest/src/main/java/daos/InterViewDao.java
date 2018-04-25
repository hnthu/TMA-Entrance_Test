package daos;
import models.Interview;

import java.util.List;

public interface InterViewDao {
    public Interview get(String interviewName);
    public Interview getInterviewById(int id);
    public List<Interview> search(String searchString);
    public List<Interview> getAll();
    public void add(Interview newInterview);
    public void update(Interview modifiedInterview);
    public void delete(int id);
}
