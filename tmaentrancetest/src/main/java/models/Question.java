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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId", nullable = false)
    private Category categoryId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kindId", nullable = false)
    private Kind kindId;
    @Column(name = "questionText", nullable = false)
    private String questionText;
    @Column(name = "correctAnswer", nullable = false)
    private String correctAnswer;
    @Column(name = "hardLevel", nullable = false)
    private int hardLevel;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "question", cascade = CascadeType.ALL)
    private Answer answerId;

    public Question() {
    }

    public Question(int questionId, Category categoryId, Kind kindId, String questionText, String correctAnswer, int level, Answer answerId) {
        this.questionId = questionId;
        this.categoryId = categoryId;
        this.kindId = kindId;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.hardLevel = level;
        this.answerId = answerId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public Category getCategory() {
        return categoryId;
    }

    public void setCategory(Category categoryId) {
        this.categoryId = categoryId;
    }

    public Kind getKind() {
        return kindId;
    }

    public void setKind(Kind kindId) {
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

    public void setLevel(int hardLevel) {
        this.hardLevel = hardLevel;
    }

    public Answer getAnswer() {
        return answerId;
    }

    public void setAnswer(Answer answerId) {
        this.answerId = answerId;
    }

}
