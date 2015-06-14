package pl.mjasion.restcache;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.mjasion.restcache.exceptions.ApiKeyNotFoundException;
import pl.mjasion.restcache.exceptions.BadCacheRequestException;
import pl.mjasion.restcache.exceptions.CacheExistsException;
import pl.mjasion.restcache.exceptions.CacheNotFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiKeyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    Map<String, String> handleApiKeyNotFoundException(ApiKeyNotFoundException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("apiKey", ex.apiKey);
        return map;
    }

    @ExceptionHandler(CacheNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    Map<String, String> handleCacheNotFoundException(CacheNotFoundException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("apiKey", ex.apiKey);
        map.put("cacheKey", ex.cacheKey);
        return map;
    }

    @ExceptionHandler(CacheExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    Map<String, String> handleCacheExistsException(CacheExistsException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("apiKey", ex.apiKey);
        map.put("cacheKey", ex.cacheKey);
        return map;
    }

    @ExceptionHandler(BadCacheRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    void handleBadCacheRequestException(BadCacheRequestException ex) {
    }
}

