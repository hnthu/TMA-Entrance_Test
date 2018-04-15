package models;
import javax.persistence.*;

@Entity
@Table(name = "questionType")
public class QuestionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "typename", nullable = false)
    private String type;

    public QuestionType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public QuestionType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTyppe() {
        return type;
    }

    public void setTyppe(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "QuestionType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
