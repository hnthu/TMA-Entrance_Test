package daos.impls;

import daos.TableDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Repository;
import services.*;

import javax.transaction.Transactional;

@Repository
@Transactional
public class TableDaoImpl implements TableDao{

    private static final org.slf4j.Logger logger =  LoggerFactory.getLogger(TableDaoImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private KindService kindService;
    @Autowired
    private InterviewService interviewService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;

    @Autowired
    private HibernateTransactionManager manager;
    @Override
    public void deleteAllRecord(String tableName, int[] selectedIds) {
        Session session = this.manager.getSessionFactory().getCurrentSession();
        if(selectedIds.length == 0){
            String hql = String.format("delete from %s",tableName);
            Query query = session.createQuery(hql);
            query.executeUpdate();
        }
        else {
            if(tableName.equals("Interview")){
                for(int a: selectedIds){
                    this.interviewService.delete(a);
                }
            }
            else if(tableName.equals("User")){
                for(int a: selectedIds){
                    this.userService.delete(a);
                }
            }
            else if(tableName.equals("Question")){
                for(int a: selectedIds){
                    this.questionService.delete(a);
                }
            }

        }

    }
}
