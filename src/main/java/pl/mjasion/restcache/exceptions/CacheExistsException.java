package pl.mjasion.restcache.exceptions;

public class CacheExistsException extends RuntimeException {
    public String apiKey;
    public String cacheKey;

    public CacheExistsException(String apiKey, String cacheKey) {
        super(cacheKey + " already exists");
        this.apiKey = apiKey;
        this.cacheKey = cacheKey;
    }
}
