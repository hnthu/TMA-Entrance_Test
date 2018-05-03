package models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "interview")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interviewId")
    private int interviewId;
    @Column(name = "interviewName")
    private String interviewName;
    @Column(name = "questionList")
    private String questionList;

    public Interview() {
    }

    public Interview(int interviewId, String interviewName, String questionList) {
        this.interviewId = interviewId;
        this.interviewName = interviewName;
        this.questionList = questionList;
    }

    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
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
                "interviewId=" + interviewId +
                ", interviewName='" + interviewName + '\'' +
                ", questionList='" + questionList + '\'' +
                '}';
    }
}
