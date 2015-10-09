package br.org.itai.biblioteca.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true, length = 15)
    private String title;

    @Column(nullable = false, unique = true, length = 25)
    private String subTitle;

    @Column(nullable = false)
    private Integer pages;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false, unique = true, length = 15)
    private String isbn;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Author> authors;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    private Category category;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    private Editor editor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", pages=" + pages +
                ", year=" + year +
                ", isbn='" + isbn + '\'' +
                ", authors=" + authors +
                ", category=" + category +
                ", editor=" + editor +
                '}';
    }
}
