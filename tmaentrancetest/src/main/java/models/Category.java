package models;

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
    private String categotyname;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "categoryId", cascade = CascadeType.ALL)
    private Set<Question> questions = new HashSet<Question>(
            0);

    public Category(int id, String categotyname, Set<Question> questions) {
        this.categoryId = id;
        this.categotyname = categotyname;
        this.questions = questions;
    }

    public Category() {
    }

    public int getId() {
        return categoryId;
    }

    public void setId(int id) {
        this.categoryId = id;
    }

    public String getCategotyname() {
        return categotyname;
    }

    public void setCategotyname(String categotyname) {
        this.categotyname = categotyname;
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
                ", categotyname='" + categotyname + '\'' +
                ", questions=" + questions +
                '}';
    }
}

