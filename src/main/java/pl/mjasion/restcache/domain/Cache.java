package pl.mjasion.restcache.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.mjasion.restcache.domain.request.CacheRequest;

@Document
@CompoundIndexes({
        @CompoundIndex(name = "api_key_idx", def = "{'key': 1, 'api': 1}"),
        @CompoundIndex(name = "api_idx", def = "{'api': 1}")
})
public class Cache {

    @Id
    private String id;

    private String api;

    private String key;

    private String value;

    public Cache() {
    }

    public Cache(String api, String key, CacheRequest cacheRequest) {
        this.api = api;
        this.key = key;
        this.value = cacheRequest.cacheValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
