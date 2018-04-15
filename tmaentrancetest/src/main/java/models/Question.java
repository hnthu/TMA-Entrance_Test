package models;

import javax.persistence.*;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "categoryid", nullable = false)
    private int categoryid;
    @Column(name = "questiontest", nullable = false)
    private String questiontest;
    @Column(name = "correctanswer", nullable = false)
    private int correctanswer;

    public Question(int id, int categoryid, String questiontest, int correctanswer) {
        this.id = id;
        this.categoryid = categoryid;
        this.questiontest = questiontest;
        this.correctanswer = correctanswer;
    }

    public Question() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getQuestiontest() {
        return questiontest;
    }

    public void setQuestiontest(String questiontest) {
        this.questiontest = questiontest;
    }

    public int getCorrectanswer() {
        return correctanswer;
    }

    public void setCorrectanswer(int correctanswer) {
        this.correctanswer = correctanswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", categoryid=" + categoryid +
                ", questiontest='" + questiontest + '\'' +
                ", correctanswer=" + correctanswer +
                '}';
    }
}
