package pl.mjasion.restcache.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import pl.mjasion.restcache.domain.Api;
import pl.mjasion.restcache.domain.Cache;

public interface CacheRepository extends CrudRepository<Cache, String> {

    Cache findByApiAndKey(String api, String key);

}
