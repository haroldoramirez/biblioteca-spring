package br.org.itai.identidade.models;

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
    private String titulo;

    @Column(nullable = false, unique = true, length = 25)
    private String subTitulo;

    @Column(nullable = false)
    private Integer paginas;

    @Column(nullable = false)
    private Integer ano;

    @Column(nullable = false, unique = true, length = 25)
    private String isbn;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Author> autores;

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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Author> getAutores() {
        return autores;
    }

    public void setAutores(List<Author> autores) {
        this.autores = autores;
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
                ", titulo='" + titulo + '\'' +
                ", subTitulo='" + subTitulo + '\'' +
                ", paginas=" + paginas +
                ", ano=" + ano +
                ", isbn='" + isbn + '\'' +
                ", autores=" + autores +
                ", category=" + category +
                ", editor=" + editor +
                '}';
    }
}
