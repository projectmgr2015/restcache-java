package pl.mjasion.restcache.exceptions;

public class CacheNotFoundException extends RuntimeException {
    public String apiKey;
    public String cacheKey;

    public CacheNotFoundException(String apiKey, String cacheKey) {
        super(cacheKey + " not found");
        this.apiKey = apiKey;
        this.cacheKey = cacheKey;
    }
}
