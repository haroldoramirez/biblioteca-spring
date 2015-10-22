package br.org.itai.biblioteca.controllers;

import br.org.itai.biblioteca.models.Author;
import br.org.itai.biblioteca.models.Book;
import br.org.itai.biblioteca.repositories.AuthorRepository;
import br.org.itai.biblioteca.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public ResponseEntity<?> inserir(@RequestBody Book book) {

        Book busca = bookRepository.findByTitle(book.getTitle());

        if (busca != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Error("O Livro: '"+busca.getTitle()+"' já esta cadastrado."));
        }

        List<Author> authors = new ArrayList<Author>();
        if(book.getAuthors() != null) {
            for (Author a:book.getAuthors()) {
                Author au = authorRepository.findOne(a.getId());
                if (a != null) {
                    authors.add(au);
                }
            }
        }

        book.setAuthors(authors);

        book = bookRepository.save(book);

        return ResponseEntity.ok(book);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> atualizar(@RequestBody Book book, @PathVariable Long id) {

        if (bookRepository.findOne(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error("O Livro não foi encontrado."));
        }

        book = bookRepository.save(book);

        return ResponseEntity.ok(book);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public Book buscaPorId(@PathVariable Long id) {
        return this.bookRepository.findOne(id);
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public Page<Book> buscaTodos(Pageable pageable) {
        return this.bookRepository.findAll(pageable);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Book book = this.bookRepository.findOne(id);

        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error("O Livro não foi encontrado."));
        }

        bookRepository.delete(book);

        return ResponseEntity.ok(book);
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET, params = "filter")
    @Transactional(readOnly = true)
    public Page<Book> filtro(@RequestParam String filter, Pageable pageable) {
        return this.bookRepository.listByFilters(filter, pageable);
    }
}
