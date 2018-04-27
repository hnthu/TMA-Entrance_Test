package models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "interview")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interviewId", nullable = false)
    private int interviewId;
    @ManyToMany(mappedBy = "interviews")
    private Set<Question> questions = new HashSet<>();
    @Column(name = "interviewName", nullable = false)
    private String interviewName;

    public Interview() {
    }

    public Interview(int interviewId, Set<Question> questions, String interviewName) {
        this.interviewId = interviewId;
        this.questions = questions;
        this.interviewName = interviewName;
    }

    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public String getInterviewName() {
        return interviewName;
    }

    public void setInterviewName(String interviewName) {
        this.interviewName = interviewName;
    }

    @Override
    public String toString() {
        return "Interview{" +
                "interviewId=" + interviewId +
                ", questions=" + questions +
                ", interviewName='" + interviewName + '\'' +
                '}';
    }
}
