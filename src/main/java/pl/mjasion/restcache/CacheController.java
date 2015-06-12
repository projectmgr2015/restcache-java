package pl.mjasion.restcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mjasion.restcache.domain.Api;
import pl.mjasion.restcache.domain.Cache;
import pl.mjasion.restcache.domain.repository.ApiRepository;
import pl.mjasion.restcache.domain.repository.CacheRepository;
import pl.mjasion.restcache.domain.request.CreateRequest;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api/{apiKey}")
public class CacheController {

    @Autowired ApiRepository apiRepository;
    @Autowired CacheRepository cacheRepository;

    @RequestMapping(value = "/", method = GET)
    public List<Cache> getAll(@PathVariable("apiKey") String apiKey) {
        validateApi(apiKey);
        return cacheRepository.findByApi(apiKey);
    }

    @RequestMapping(value = "/{key}", method = GET)
    public Cache get(@PathVariable("apiKey") String apiKey, @PathVariable("key") String key) {
        validateApi(apiKey);
        return cacheRepository.findByApiAndKey(apiKey, key);
    }

    @RequestMapping(value = "/{key}", method = POST)
    public void create(@PathVariable("apiKey") String apiKey, @PathVariable("key") String key, CreateRequest createRequest) {
        validateApi(apiKey);
        if (cacheRepository.findByApiAndKey(apiKey, key) != null) {
            throw new RuntimeException("Key  exists");
        }
        Cache cache = new Cache(apiKey, key, createRequest);
        cacheRepository.save(cache);
    }

    @RequestMapping(value = "/{key}", method = PUT)
    public void update(@PathVariable("apiKey") String apiKey, @PathVariable("key") String key,
                       CreateRequest createRequest) {
        validateApi(apiKey);
        if (cacheRepository.findByApiAndKey(apiKey, key) == null) {
            throw new RuntimeException("Key  not found");
        }
        Cache cache = new Cache(apiKey, key, createRequest);
        cacheRepository.save(cache);
    }

    @RequestMapping(value = "/{key}", method = DELETE)
    public void delete(@PathVariable("apiKey") String apiKey, @PathVariable("key") String key,
                       CreateRequest createRequest) {
        validateApi(apiKey);
        cacheRepository.deleteByApiAndKey(apiKey, key);
    }

    private void validateApi(@PathVariable("apiKey") String apiKey) {
        Api api = apiRepository.findOne(apiKey);
        if (api == null) {
            throw new RuntimeException("API KEY not found");
        }
    }


}
