package br.org.itai.biblioteca.controllers;

import br.org.itai.biblioteca.models.Category;
import br.org.itai.biblioteca.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public ResponseEntity<?> inserir(@RequestBody Category category) {

        Category busca = categoryRepository.findByName(category.getName());

        if (busca != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Error("A Categoria: '"+busca.getName()+"' já esta cadastrada."));
        }

        category = categoryRepository.save(category);

        return ResponseEntity.ok(category);
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> atualizar(@RequestBody Category category, @PathVariable Long id) {

        if (categoryRepository.findOne(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error("A Categoria não foi encontrada."));
        }

        category = categoryRepository.save(category);

        return ResponseEntity.ok(category);
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public Category buscaPorId(@PathVariable Long id) {
        return this.categoryRepository.findOne(id);
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public Page<Category> buscaTodos(Pageable pageable) {
        return this.categoryRepository.findAll(pageable);
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Category category = this.categoryRepository.findOne(id);

        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error("A Categoria não foi encontrada."));
        }

        categoryRepository.delete(category);

        return ResponseEntity.ok(category);
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET, params = "filter")
    @Transactional(readOnly = true)
    public Page<Category> filtro(@RequestParam String filter, Pageable pageable) {
        return this.categoryRepository.listByFilters(filter, pageable);
    }
}
