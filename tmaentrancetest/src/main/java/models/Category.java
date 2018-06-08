package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId", nullable = false)
    private int categoryId;
    @Column(name = "categoryName", nullable = false)
    private String categoryName;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "categoryId", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Question> questions = new HashSet<Question>(
            0);
    @Formula("(select count(q.questionId) from question q where q.categoryId = categoryId)")
    private int numberQuestion;
    @Formula("(SELECT SUM(CASE WHEN q.kindId = '1' or q.kindId = '2' THEN 1 ELSE 0 END) FROM category c\n" +
            " LEFT JOIN question q ON (q.categoryId = c.categoryId) where c.categoryId = categoryId)")
    private int multipleChoiceQuestion;
    @Formula("(SELECT SUM(CASE WHEN q.kindId = '3' THEN 1 ELSE 0 END) FROM category c\n" +
            " LEFT JOIN question q ON (q.categoryId = c.categoryId) where c.categoryId = categoryId)")
    private int shortQuestion;

    public Category() {
    }

    public Category(int categoryId, String categoryName, Set<Question> questions, int numberQuestion, int multipleChoiceQuestion, int shortQuestion) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.questions = questions;
        this.numberQuestion = numberQuestion;
        this.multipleChoiceQuestion = multipleChoiceQuestion;
        this.shortQuestion = shortQuestion;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public int getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(int numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

    public int getMultipleChoiceQuestion() {
        return multipleChoiceQuestion;
    }

    public void setMultipleChoiceQuestion(int multipleChoiceQuestion) {
        this.multipleChoiceQuestion = multipleChoiceQuestion;
    }

    public int getShortQuestion() {
        return shortQuestion;
    }

    public void setShortQuestion(int shortQuestion) {
        this.shortQuestion = shortQuestion;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", questions=" + questions +
                ", numberQuestion=" + numberQuestion +
                ", multipleChoiceQuestion=" + multipleChoiceQuestion +
                ", shortQuestion=" + shortQuestion +
                '}';
    }
}

