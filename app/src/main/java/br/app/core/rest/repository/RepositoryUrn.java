package br.app.core.rest.repository;

import br.app.core.rest.model.HttpUrl;
import br.app.core.rest.model.HttpUrn;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUrn extends CrudRepository<HttpUrn, Long> {

    @Query("SELECT u FROM HttpUrn u WHERE u.name = ?1")
    HttpUrn FindURNByName(String httpUrn);

}
