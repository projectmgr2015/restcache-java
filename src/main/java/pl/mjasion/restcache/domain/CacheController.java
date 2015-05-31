package pl.mjasion.restcache.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.mjasion.restcache.domain.repository.ApiRepository;
import pl.mjasion.restcache.domain.repository.CacheRepository;
import pl.mjasion.restcache.domain.request.CreateRequest;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/{apiKey}")
public class CacheController {

    @Autowired ApiRepository apiRepository;
    @Autowired CacheRepository cacheRepository;

    @RequestMapping(value = "/{key}", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("apiKey") String apiKey, @PathVariable("key") String key, CreateRequest createRequest) {
        System.out.println("apiKey = " + apiKey);
        System.out.println("key = " + key);
        System.out.println("createRequest.cacheValue = " + createRequest.cacheValue);
        System.out.println("createRequest.expire = " + createRequest.expire);
        Api api = apiRepository.findOne(apiKey);
        if (api == null) {
            throw new RuntimeException("Kaput");
        }
        Cache cache = new Cache(apiKey, key, createRequest);
        cacheRepository.save(cache);
    }

}
