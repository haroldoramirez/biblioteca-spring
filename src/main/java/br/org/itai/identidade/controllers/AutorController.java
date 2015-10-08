package br.org.itai.identidade.controllers;

import br.org.itai.identidade.models.Autor;
import br.org.itai.identidade.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AutorController {

    @Autowired
    AutorService autorService;

    @RequestMapping(value = "/autores", method = RequestMethod.POST)
    public Autor inserir(@RequestBody Autor autor) {
        return this.autorService.inserir(autor);
    }

    @RequestMapping(value = "/autores/{id}", method = RequestMethod.PUT)
    public Autor atualizar(@RequestBody Autor autor, @PathVariable Long id) {
        return this.autorService.atualizar(autor);
    }

    @RequestMapping(value = "/autores/{id}", method = RequestMethod.GET)
    public Autor buscaPorId(@PathVariable Long id) {
        return this.autorService.buscaPorId(id);
    }

    @RequestMapping(value = "/autores", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public List<Autor> buscaTodos() {
        return this.autorService.buscaTodos();
    }

    @RequestMapping(value = "/autores/{id}", method = RequestMethod.DELETE)
    public void remover(@PathVariable Long id) {
        this.autorService.remover(id);
    }

}
