package models;

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
    private Category categoryId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kindId", nullable = false)
    private Kind kindId;
    @Column(name = "questionText", nullable = false)
    private String questionText;
    @Column(name = "correctAnswer", nullable = false)
    private int correctAnswer;
    @Column(name = "level", nullable = false)
    private int level;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "question", cascade = CascadeType.ALL)
    private Answer answer;

    public Question() {
    }

    public Question(int questionId, Category categoryId, Kind kindId, String questionText, int correctAnswer, int level, Answer answer) {
        this.questionId = questionId;
        this.categoryId = categoryId;
        this.kindId = kindId;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.level = level;
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

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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
//                ", level=" + level +
//                ", answer=" + answer +
//                '}';
//    }
}
