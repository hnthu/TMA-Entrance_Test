package models;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "kind")
public class Kind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kindId", nullable = false)
    private int kindId;
    @Column(name = "kindName", nullable = false)
    private String kindName;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "kindId")
    @JsonIgnore
    private Set<Question> questions = new HashSet<Question>(
            0);

    public Kind() {
    }

    public Kind(int kindId, String kindName, Set<Question> questions) {
        this.kindId = kindId;
        this.kindName = kindName;
        this.questions = questions;
    }

    public int getKindId() {
        return kindId;
    }

    public void setKindId(int kindId) {
        this.kindId = kindId;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Kind{" +
                "kindId=" + kindId +
                ", kindName='" + kindName + '\'' +
                ", questions=" + questions +
                '}';
    }
}
