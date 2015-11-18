package br.org.itai.biblioteca.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true, length = 25)
    private String name;

    //TODO
    //@ManyToOne(cascade = CascadeType.MERGE, optional = false)
    //private Category parent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Category getParent() {
//        return parent;
//    }
//
//    public void setParent(Category parent) {
//        this.parent = parent;
//    }

    @Override
    public String toString() {
        return "CategoryRepository{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
