package br.org.itai.identidade.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Livro implements Serializable {

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
    private List<Autor> autores;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    private Categoria categoria;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    private Editora editora;

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

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", subTitulo='" + subTitulo + '\'' +
                ", paginas=" + paginas +
                ", ano=" + ano +
                ", isbn='" + isbn + '\'' +
                ", autores=" + autores +
                ", categoria=" + categoria +
                ", editora=" + editora +
                '}';
    }
}
