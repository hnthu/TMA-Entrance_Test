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
    @Column(name = "answerList")
    private String answerList;

    public Interview() {
    }

    public Interview(int interviewId, String interviewName, String questionList, String answerList) {
        this.interviewId = interviewId;
        this.interviewName = interviewName;
        this.questionList = questionList;
        this.answerList = answerList;
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

    public String getAnswerList() {
        return answerList;
    }

    public void setAnswerList(String answerList) {
        this.answerList = answerList;
    }

    @Override
    public String toString() {
        return "Interview{" +
                "interviewId=" + interviewId +
                ", interviewName='" + interviewName + '\'' +
                ", questionList='" + questionList + '\'' +
                ", answerList='" + answerList + '\'' +
                '}';
    }
}
