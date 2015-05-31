package pl.mjasion.restcache.domain.repository;

import org.springframework.data.repository.Repository;
import pl.mjasion.restcache.domain.Api;

public interface ApiRepository extends Repository<Api, String> {

    Api findOne(String key);

    Api save(Api api);



}
