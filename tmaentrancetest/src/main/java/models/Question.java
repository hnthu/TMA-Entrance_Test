package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionId", nullable = false)
    private int questionId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", nullable = false)
    @JsonIgnore
    private Category categoryId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "kindId", nullable = false)
    private Kind kindId;
    @Column(name = "questionText", nullable = false)
    private String questionText;
    @Column(name = "correctAnswer", nullable = false)
    private String correctAnswer;
    @Column(name = "hardLevel", nullable = false)
    private int hardLevel;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "question", cascade = CascadeType.ALL)
    @JsonIgnore
    private Answer answer;

    public Question() {
    }

    public Question(int questionId, Category categoryId, Kind kindId, String questionText, String correctAnswer, int level, Answer answer) {
        this.questionId = questionId;
        this.categoryId = categoryId;
        this.kindId = kindId;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.hardLevel = level;
        this.answer = answer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public Kind getKindId() {
        return kindId;
    }

    public void setKindId(Kind kindId) {
        this.kindId = kindId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getLevel() {
        return hardLevel;
    }

    public void setLevel(int level) {
        this.hardLevel = level;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

//    @Override
//    public String toString() {
//        return "Question{" +
//                "questionId=" + questionId +
//                ", categoryId=" + categoryId +
//                ", kindId=" + kindId +
//                ", questionText='" + questionText + '\'' +
//                ", correctAnswer=" + correctAnswer +
//                ", hardLevel=" + hardLevel +
//                ", answer=" + answer +
//                '}';
//    }
}
