package pl.mjasion.restcache.domain.repository;

import org.springframework.data.repository.CrudRepository;
import pl.mjasion.restcache.domain.Cache;

public interface CacheRepository extends CrudRepository<Cache, String> {

    Cache findByApiAndKey(String api, String key);

    Long deleteByApiAndKey(String api, String key);
}
