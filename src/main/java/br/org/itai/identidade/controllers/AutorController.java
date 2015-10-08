package br.org.itai.identidade.controllers;

import br.org.itai.identidade.models.Author;
import br.org.itai.identidade.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AutorController {

    @Autowired
    AutorRepository autorRepository;

    @RequestMapping(value = "/autores", method = RequestMethod.POST)
    public ResponseEntity<?> inserir(@RequestBody Author author) {

        Author busca = autorRepository.findByName(author.getName());

        if (busca != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Error("Author já cadastrado"));
        }

        author = autorRepository.save(author);

        return ResponseEntity.ok(author);
    }

    @RequestMapping(value = "/autores/{id}", method = RequestMethod.PUT)
    public Author atualizar(@RequestBody Author author, @PathVariable Long id) {
        return this.autorRepository.save(author);
    }

    @RequestMapping(value = "/autores/{id}", method = RequestMethod.GET)
    public Author buscaPorId(@PathVariable Long id) {
        return this.autorRepository.findOne(id);
    }

    @RequestMapping(value = "/autores", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public List<Author> buscaTodos() {
        return this.autorRepository.findAll();
    }

    @RequestMapping(value = "/autores/{id}", method = RequestMethod.DELETE)
    public void remover(@PathVariable Long id) {
        Author author = this.autorRepository.findOne(id);
        this.autorRepository.delete(author);
    }

}
