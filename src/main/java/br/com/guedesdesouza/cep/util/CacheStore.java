package br.com.guedesdesouza.cep.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Slf4j
public class CacheStore<K, T> {

    private static final long DEFAULT_MAX_SIZE = 100;

    private final Cache<K, T> cache;

    public CacheStore(int expiryDuration, TimeUnit timeUnit) {
        this(expiryDuration, timeUnit, DEFAULT_MAX_SIZE);
    }

    public CacheStore(int expiryDuration, TimeUnit timeUnit, long maxSize) {
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(expiryDuration, timeUnit)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .maximumSize(Optional.ofNullable(maxSize).orElse(DEFAULT_MAX_SIZE))
                .build();
    }

    public T get(K key, Callable<? extends T> callable) {
        try {
            return cache.get(key, callable);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            return null;
        }
    }

    public void add(K key, T value) {
        cache.put(key, value);
    }

    private void invalidateAll() {
        cache.invalidateAll();
    }

    private void invalidate(K key) {
        cache.invalidate(key);
    }

    private void invalidateBy(Function<K, Boolean> condition) {
        cache.asMap().keySet().stream().filter(condition::apply).forEach(this::invalidate);
    }

}
