package br.org.itai.identidade.repositories;


import br.org.itai.identidade.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Author, Long> {


    Author findByName(String name);

}
