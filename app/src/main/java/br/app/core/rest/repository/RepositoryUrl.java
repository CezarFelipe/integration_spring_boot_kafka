package br.app.core.rest.repository;

import br.app.core.rest.model.HttpUrl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUrl extends CrudRepository<HttpUrl,Long> {

        @Query("SELECT u FROM HttpUrl u WHERE u.name = ?1")
        HttpUrl FindURLByName(String urlName);
}
