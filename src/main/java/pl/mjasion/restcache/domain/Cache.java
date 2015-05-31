package pl.mjasion.restcache.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.mjasion.restcache.domain.request.CreateRequest;

import java.net.CacheRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Document
@CompoundIndexes({
        @CompoundIndex(name = "api_key_idx", def = "{'key': 1, 'api': 1}")
})
public class Cache {

    @Id
    private String id;

    private String api;

    private String key;

    private String value;

    private Date validTo;

    @Indexed
    private Date added = new Date();

    public Cache(String api, String key, CreateRequest createRequest) {
        this.api = api;
        this.key = key;
        this.value = createRequest.cacheValue;
        LocalDateTime validTo = LocalDateTime.now().plusSeconds(createRequest.expire);
        this.validTo = Date.from(validTo.toInstant(ZoneOffset.UTC));
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

    public Date getAdded() {
        return added;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }
}
