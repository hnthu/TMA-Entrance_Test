package models;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "categoryname", nullable = false)
    private String categotyname;

    public Category(int id, String categoty) {
        this.id = id;
        this.categotyname = categoty;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoty() {
        return categotyname;
    }

    public void setCategoty(String categoty) {
        this.categotyname = categoty;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}

