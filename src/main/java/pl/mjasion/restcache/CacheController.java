package pl.mjasion.restcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mjasion.restcache.domain.Api;
import pl.mjasion.restcache.domain.Cache;
import pl.mjasion.restcache.domain.repository.ApiRepository;
import pl.mjasion.restcache.domain.repository.CacheRepository;
import pl.mjasion.restcache.domain.request.CreateRequest;
import pl.mjasion.restcache.exceptions.ApiKeyNotFoundException;
import pl.mjasion.restcache.exceptions.BadCacheRequestException;
import pl.mjasion.restcache.exceptions.CacheExistsException;
import pl.mjasion.restcache.exceptions.CacheNotFoundException;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api/{apiKey}")
public class CacheController {

    @Autowired ApiRepository apiRepository;
    @Autowired CacheRepository cacheRepository;

    @RequestMapping(method = GET)
    public List<Cache> getAll(@PathVariable("apiKey") String apiKey) {
        validateApi(apiKey);
        return cacheRepository.findByApi(apiKey);
    }

    @RequestMapping(value = "/{key}", method = GET)
    public Cache get(@PathVariable("apiKey") String apiKey, @PathVariable("key") String key) {
        validateApi(apiKey);
        cacheExists(apiKey, key);
        Cache cache = cacheRepository.findByApiAndKey(apiKey, key);
        return cache;
    }

    @RequestMapping(value = "/{key}", method = POST)
    public void create(
            @PathVariable("apiKey") String apiKey, @PathVariable("key") String key, @RequestBody CreateRequest createRequest
    ) {
        validateRequest(createRequest);
        validateApi(apiKey);
        cacheNotExists(apiKey, key);
        Cache cache = new Cache(apiKey, key, createRequest);
        cacheRepository.save(cache);
    }

    @RequestMapping(value = "/{key}", method = PUT)
    public void update(
            @PathVariable("apiKey") String apiKey, @PathVariable("key") String key, @RequestBody CreateRequest updateRequest
    ) {
        validateRequest(updateRequest);
        validateApi(apiKey);
        cacheExists(apiKey, key);
        Cache cacheToUpdate = cacheRepository.findByApiAndKey(apiKey, key);
        cacheToUpdate.setValue(updateRequest.cacheValue);
        cacheRepository.save(cacheToUpdate);
    }

    @RequestMapping(value = "/{key}", method = DELETE)
    public void delete(@PathVariable("apiKey") String apiKey, @PathVariable("key") String key) {
        validateApi(apiKey);
        cacheExists(apiKey, key);
        cacheRepository.deleteByApiAndKey(apiKey, key);
    }

    private void validateRequest(CreateRequest createRequest) {
        if(createRequest.cacheValue == null) {
            throw new BadCacheRequestException("CacheValue is null");
        }
    }

    private void validateApi(String apiKey) {
        Api api = apiRepository.findOne(apiKey);
        if (api == null) {
            throw new ApiKeyNotFoundException(apiKey);
        }
    }

    private void cacheNotExists(String apiKey, String key) {
        if (cacheRepository.findByApiAndKey(apiKey, key) != null) {
            throw new CacheExistsException(apiKey, key);
        }
    }

    private void cacheExists(String apiKey, String key) {
        if (cacheRepository.findByApiAndKey(apiKey, key) == null) {
            throw new CacheNotFoundException(apiKey, key);
        }
    }

}
