package models;
import javax.persistence.*;

@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "questionid", nullable = false)
    private int questionid;
    @Column(name = "answer")
    private String answer;

    public Answer(int id, int questionid, String answer) {
        this.id = id;
        this.questionid = questionid;
        this.answer = answer;
    }

    public Answer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", questionid=" + questionid +
                ", answer='" + answer + '\'' +
                '}';
    }
}
