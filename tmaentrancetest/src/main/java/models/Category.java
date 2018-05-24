package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "categoryId", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Question> questions = new HashSet<Question>(
            0);

    public Category(int categoryId, String categoryName, Set<Question> questions) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.questions = questions;
    }

    public Category() {
    }

    public int getId() {
        return categoryId;
    }

    public void setId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryname() {
        return categoryName;
    }

    public void setCategoryname(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", questions=" + questions +
                '}';
    }
}

