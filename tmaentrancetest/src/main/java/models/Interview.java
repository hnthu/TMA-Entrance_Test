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
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "categoryId", cascade = CascadeType.ALL)
    private Set<Question> questions = new HashSet<Question>(
            0);

    public Interview() {
    }

    public Interview(int interviewId, String interviewName, Set<Question> questions) {
        this.interviewId = interviewId;
        this.interviewName = interviewName;
        this.questions = questions;
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

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Interview{" +
                "interviewId=" + interviewId +
                ", interviewName='" + interviewName + '\'' +
                ", questions=" + questions +
                '}';
    }
}
