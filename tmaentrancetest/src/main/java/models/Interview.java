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
    private String interviewname;
    @Column(name = "questionlist", nullable = false)
    private String questionlist;

    public Interview() {
    }

    public Interview(int id, String interviewname, String questionlist) {
        this.id = id;
        this.interviewname = interviewname;
        this.questionlist = questionlist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInterviewName() {
        return interviewname;
    }

    public void setInterviewName(String interviewName) {
        this.interviewname = interviewName;
    }

    public String getQuestionList() {
        return questionlist;
    }

    public void setQuestionList(String questionList) {
        this.questionlist = questionList;
    }

    @Override
    public String toString() {
        return "Interview{" +
                "id=" + id +
                ", interviewname='" + interviewname + '\'' +
                ", questionlist='" + questionlist + '\'' +
                '}';
    }
}
