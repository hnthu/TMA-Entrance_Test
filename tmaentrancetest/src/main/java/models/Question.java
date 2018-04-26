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
    private int questiontypeid;
    @Column(name = "questiontypeid", nullable = false)
    private int categoryid;
    @Column(name = "questiontext", nullable = false)
    private String questiontext;
    @Column(name = "correctanswer", nullable = false)
    private int correctanswer;
    @Column(name = "level", nullable = false)
    private int level;

    public Question(int id, int questiontypeid, int categoryid, String questiontext, int correctanswer, int level) {
        this.id = id;
        this.questiontypeid = questiontypeid;
        this.categoryid = categoryid;
        this.questiontext = questiontext;
        this.correctanswer = correctanswer;
        this.level = level;
    }

    public Question() {
    }

    public int getQuestiontypeid() {
        return questiontypeid;
    }

    public void setQuestiontypeid(int questiontypeid) {
        this.questiontypeid = questiontypeid;
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

    public String getQuestiontext() {
        return questiontext;
    }

    public void setQuestiontext(String questiontext) {
        this.questiontext = questiontext;
    }

    public int getCorrectanswer() {
        return correctanswer;
    }

    public void setCorrectanswer(int correctanswer) {
        this.correctanswer = correctanswer;
    }

    public int getLevel() { return level;}

    public void setLevel(int level) { this.level = level;}

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questiontypeid=" + questiontypeid +
                ", categoryid=" + categoryid +
                ", questiontext='" + questiontext + '\'' +
                ", correctanswer=" + correctanswer +
                ", level=" + level +
                '}';
    }
}
