package pl.mjasion.restcache.domain.repository;

import org.springframework.data.repository.CrudRepository;
import pl.mjasion.restcache.domain.Cache;

import java.util.List;

public interface CacheRepository extends CrudRepository<Cache, String> {

    List<Cache> findByApi(String api);

    Cache findByApiAndKey(String api, String key);

    Long deleteByApiAndKey(String api, String key);
}
