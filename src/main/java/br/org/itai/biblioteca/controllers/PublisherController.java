package br.org.itai.biblioteca.controllers;

import br.org.itai.biblioteca.models.Publisher;
import br.org.itai.biblioteca.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class PublisherController {

    @Autowired
    PublisherRepository publisherRepository;

    @RequestMapping(value = "/publishers", method = RequestMethod.POST)
    public ResponseEntity<?> inserir(@RequestBody Publisher publisher) {

        Publisher busca = publisherRepository.findByName(publisher.getName());

        if (busca != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Error("A Editora: '"+busca.getName()+"' já esta cadastrada."));
        }

        publisher = publisherRepository.save(publisher);

        return ResponseEntity.ok(publisher);
    }

    @RequestMapping(value = "/publishers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> atualizar(@RequestBody Publisher publisher, @PathVariable Long id) {

        if (publisherRepository.findOne(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error("A Editora não foi encontrada."));
        }

        publisher = publisherRepository.save(publisher);

        return ResponseEntity.ok(publisher);
    }

    @RequestMapping(value = "/publishers/{id}", method = RequestMethod.GET)
    public Publisher buscaPorId(@PathVariable Long id) {
        return this.publisherRepository.findOne(id);
    }

    @RequestMapping(value = "/publishers", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public Page<Publisher> buscaTodos(Pageable pageable) {
        return this.publisherRepository.findAll(pageable);
    }

    @RequestMapping(value = "/publishers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Publisher publisher = this.publisherRepository.findOne(id);

        if (publisher == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error("A Editora não foi encontrada."));
        }

        publisherRepository.delete(publisher);

        return ResponseEntity.ok(publisher);
    }

    @RequestMapping(value = "/publishers", method = RequestMethod.GET, params = "filter")
    @Transactional(readOnly = true)
    public Page<Publisher> filtro(@RequestParam String filter, Pageable pageable) {
        return this.publisherRepository.listByFilters(filter, pageable);
    }
}
