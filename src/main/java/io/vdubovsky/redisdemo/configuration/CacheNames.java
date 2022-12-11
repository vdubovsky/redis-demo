package io.vdubovsky.redisdemo.configuration;

import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;

@Getter
public enum CacheNames {

    USER_CACHE("users_cache");

    private final String cacheName;

    CacheNames(String cacheName) {
        this.cacheName = cacheName;
    }

    public static String[] getAllCacheNames(){
        return Arrays.stream(CacheNames.values()).map(CacheNames::getCacheName).toArray(String[]::new);
    }
}
