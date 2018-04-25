package models;

import javax.persistence.*;

@Entity
@Table(name = "interview")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "interviewname", nullable = false)
    private String interviewName;
    @Column(name = "questionlist", nullable = false)
    private String questionList;

    public Interview() {
    }

    public Interview(int id, String interviewName, String questionList) {
        this.id = id;
        this.interviewName = interviewName;
        this.questionList = questionList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInterviewName() {
        return interviewName;
    }

    public void setInterviewName(String interviewName) {
        this.interviewName = interviewName;
    }

    public String getQuestionList() {
        return questionList;
    }

    public void setQuestionList(String questionList) {
        this.questionList = questionList;
    }

    @Override
    public String toString() {
        return "Interview{" +
                "id=" + id +
                ", interviewName='" + interviewName + '\'' +
                ", questionList='" + questionList + '\'' +
                '}';
    }
}
