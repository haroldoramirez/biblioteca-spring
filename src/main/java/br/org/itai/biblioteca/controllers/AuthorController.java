package br.org.itai.biblioteca.controllers;

import br.org.itai.biblioteca.models.Author;
import br.org.itai.biblioteca.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @RequestMapping(value = "/authors", method = RequestMethod.POST)
    public ResponseEntity<?> inserir(@RequestBody Author author) {

        Author busca = authorRepository.findByName(author.getName());

        if (busca != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Error("O Autor: '"+busca.getName()+"' já cadastrado."));
        }

        author = authorRepository.save(author);

        return ResponseEntity.ok(author);
    }

    @RequestMapping(value = "/authors/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> atualizar(@RequestBody Author author, @PathVariable Long id) {

        if (authorRepository.findOne(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error("O Autor não foi encontrado."));
        }

        author = authorRepository.save(author);

        return ResponseEntity.ok(author);
    }

    @RequestMapping(value = "/authors/{id}", method = RequestMethod.GET)
    public Author buscaPorId(@PathVariable Long id) {
        return this.authorRepository.findOne(id);
    }

    @RequestMapping(value = "/authors", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @RequestMapping(value = "/authors/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Author author = this.authorRepository.findOne(id);

        if (author == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error("O Autor não foi encontrado."));
        }

        authorRepository.delete(author);

        return ResponseEntity.ok(author);
    }

    @RequestMapping(value = "/authors/filter/{id}", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public List<Author> filtro(@PathVariable String filter) {

        System.out.print("pegou o método filtro");
        return this.authorRepository.findAll();
    }

}
