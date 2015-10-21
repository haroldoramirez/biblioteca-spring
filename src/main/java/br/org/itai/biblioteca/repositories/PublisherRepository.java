package br.org.itai.biblioteca.repositories;

import br.org.itai.biblioteca.models.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    Publisher findByName(String name);

    @Query( "select a from Publisher a where a.name like '%' || :filters || '%' ")
    Page<Publisher> listByFilters( @Param("filters") String filter, Pageable pageable );
}
