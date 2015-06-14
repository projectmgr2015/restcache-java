package pl.mjasion.restcache.exceptions;

public class ApiKeyNotFoundException extends RuntimeException {
    public String apiKey;

    public ApiKeyNotFoundException(String apiKey) {
        super(apiKey + " not found");
        this.apiKey = apiKey;
    }
}
