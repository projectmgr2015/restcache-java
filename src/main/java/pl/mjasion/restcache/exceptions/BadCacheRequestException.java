package pl.mjasion.restcache.exceptions;

public class BadCacheRequestException extends RuntimeException {
    public BadCacheRequestException(String msg) {
        super(msg);
    }
}
