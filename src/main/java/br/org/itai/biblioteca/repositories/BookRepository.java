package br.org.itai.biblioteca.repositories;

import br.org.itai.biblioteca.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByTitle(String title);

    @Query( "select a from Book a where a.title like '%' || :filters || '%' ")
    Page<Book> listByFilters( @Param("filters") String filter, Pageable pageable );
}
