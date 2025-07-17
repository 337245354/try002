package Util;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import lombok.val;

public final class LocalCache {

    private static final String DELIMITER = ":";

    private LocalCache() {

    }

    private static final Cache<String, Object> CACHE = Caffeine.newBuilder()
            .initialCapacity(2_000)
            .maximumSize(2_000)
            .expireAfterWrite(30, TimeUnit.SECONDS)
            .build();

    public static <K, T> T get(K key, Function<K, T> mappingFunction) {
        String cacheKey = buildCacheKey(key);
        T cachedValue = (T) CACHE.getIfPresent(cacheKey);
        if (cachedValue != null) {
            return cachedValue;
        } else {
            T result = mappingFunction.apply(key);
            if (result != null) {
                CACHE.put(cacheKey, result);
            }
            return result;
        }
    }

    private static <K> String buildCacheKey(K key) {
        String keyString = key.toString();
        String tenant = "";
        if (tenant != null) {
            return tenant + DELIMITER + keyString;
        }
        return keyString;
    }

}