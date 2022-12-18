package io.vdubovsky.redisdemo.service;

import io.vdubovsky.redisdemo.model.User;
import io.vdubovsky.redisdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.vdubovsky.redisdemo.configuration.CacheNames.USER_CACHE;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CacheManager cacheManager;

    @Override
    public User create(User user) {
        user.setId(UUID.randomUUID());
        return userRepository.save(user);
    }

    @Override
    public User getById(UUID id) {
        Cache cache = cacheManager.getCache(USER_CACHE.getCacheName());
        if (cache != null) {
            User user = cache.get(id, User.class);
            if (user != null) {
                log.info("User found in cache, returning user: {}", user);
                return user;
            }
        }
        log.info("User not found in cache, checking db...");
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            log.info("User found in db, putting in cache, user: {}", user);
            if (cache != null) {
                cache.put(id, user.get());
            }
            return user.get();
        }
        throw new RuntimeException("User not found, id: " + id);
    }

    @Override
    @Cacheable("users_cache")
    public User getByIdCacheable(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found, id: " + id));
    }

    @Override
    @Cacheable("users_cache_all_v")
    public List<User> getAllStartFromV() {
        return userRepository.findAll().stream().filter(u->u.getName().startsWith("V")).collect(Collectors.toList());
    }

    @Override
    @Cacheable("users_cache_all")
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
