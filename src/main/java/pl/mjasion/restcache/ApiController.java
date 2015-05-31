package pl.mjasion.restcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mjasion.restcache.domain.Api;
import pl.mjasion.restcache.domain.repository.ApiRepository;

import java.util.UUID;

@RestController
public class ApiController {

    @Autowired ApiRepository apiRepository;

    @RequestMapping("/api")
    public Api getApiKey() {
        String key = UUID.randomUUID().toString();
        Api api = new Api(key);
        apiRepository.save(api);
        return api;
    }
}
