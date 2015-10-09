package br.org.itai.biblioteca.repositories;


import br.org.itai.biblioteca.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {


    Author findByName(String name);

}
