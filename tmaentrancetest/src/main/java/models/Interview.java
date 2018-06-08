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
    @Column(name = "interviewCode")
    private String interviewCode;
    @Column(name = "interviewName")
    private String interviewName;
    @Column(name = "questionList")
    private String questionList;
    @Column(name = "answerList")
    private String answerList;
    @Column(name = "categoryName")
    private String categoryName;
    @Column(name = "description")
    private String description;

    public Interview() {
    }

    public Interview(int interviewId, String interviewCode, String interviewName, String questionList, String answerList,  String categoryName, String description) {
        this.interviewId = interviewId;
        this.interviewCode = interviewCode;
        this.interviewName = interviewName;
        this.questionList = questionList;
        this.answerList = answerList;
        this.description = description;
        this.categoryName = categoryName;
    }

    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
    }

    public String getInterviewCode() {
        return interviewCode;
    }

    public void setInterviewCode(String interviewCode) {
        this.interviewCode = interviewCode;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Interview{" +
                "interviewId=" + interviewId +
                ", interviewCode='" + interviewCode + '\'' +
                ", interviewName='" + interviewName + '\'' +
                ", questionList='" + questionList + '\'' +
                ", answerList='" + answerList + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
