package br.org.itai.biblioteca.controllers;

import br.org.itai.biblioteca.models.Author;
import br.org.itai.biblioteca.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @RequestMapping(value = "/authors", method = RequestMethod.POST)
    public ResponseEntity<?> inserir(@RequestBody Author author) {

        Author busca = authorRepository.findByName(author.getName());

        if (busca != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Error("O Autor: '"+busca.getName()+"' já esta cadastrado."));
        }

        try {
            author = authorRepository.save(author);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("Erro interno de sistema."));
        }

        return ResponseEntity.ok(author);
    }

    @RequestMapping(value = "/authors/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> atualizar(@RequestBody Author author, @PathVariable Long id) {

        if (authorRepository.findOne(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error("O Autor não foi encontrado."));
        }

        try {
            author = authorRepository.save(author);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("Erro interno de sistema."));
        }

        return ResponseEntity.ok(author);
    }

    @RequestMapping(value = "/authors/{id}", method = RequestMethod.GET)
    public Author buscaPorId(@PathVariable Long id) {
        return this.authorRepository.findOne(id);
    }

    @RequestMapping(value = "/authors", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public Page<Author> buscaTodos(Pageable pageable) {
        return this.authorRepository.findAll(pageable);
    }

    @RequestMapping(value = "/authors/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Author author = this.authorRepository.findOne(id);

        if (author == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error("O Autor não foi encontrado."));
        }

        try {
            authorRepository.delete(author);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("Existem livros que dependem deste Autor."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("Erro interno de sistema."));
        }

        return ResponseEntity.ok(author);
    }

    @RequestMapping(value = "/authors", method = RequestMethod.GET, params = "filter")
    @Transactional(readOnly = true)
    public Page<Author> filtro(@RequestParam String filter, Pageable pageable) {
        return this.authorRepository.listByFilters(filter, pageable);
    }

}
