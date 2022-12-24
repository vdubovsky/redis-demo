package io.vdubovsky.redisdemo.service;

import com.google.gson.Gson;
import io.vdubovsky.redisdemo.model.Game;
import io.vdubovsky.redisdemo.repository.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.redisson.api.listener.SetObjectListener;
import org.redisson.api.map.event.EntryEvent;
import org.redisson.api.map.event.EntryExpiredListener;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final RedissonClient redissonClient;

    private final CacheManager cacheManager;

    private final Gson gson;

    public GameServiceImpl(GameRepository gameRepository, RedissonClient redissonClient, CacheManager cacheManager, Gson gson) {
        this.gameRepository = gameRepository;
        this.redissonClient = redissonClient;
        this.cacheManager = cacheManager;
        this.gson = gson;
    }

    @Override
    public Game create(Game game) throws InterruptedException {
        game.setId(UUID.randomUUID());
        RBucket<String> gameBucket = getGameBucket(game.getId().toString());
        gameBucket.addListenerAsync(new SetObjectListener() {
            @Override
            public void onSet(String name) {
                log.info("SET");
            }
        });
        Thread.sleep(10000);
        gameBucket.set(gson.toJson(game),15, TimeUnit.SECONDS);
        gameBucket.addListener(new DeletedObjectListener() {
            @Override
            public void onDeleted(String name) {
                log.info("Delete bucket : {}", name);
                gameRepository.save(game);
            }
        });
        gameBucket.delete();

       /* RMapCache<Object, Object> games = redissonClient.getMapCache("games");
        games.put("1","2",15,TimeUnit.SECONDS);
        games.addListener(new EntryExpiredListener<String, String>() {
            @Override
            public void onExpired(EntryEvent<String, String> event) {
                log.info("EXPIRED");
            }
        });*/
        return game;
    }



    @Override
    public Game getById(UUID id) {
        RBucket<String> gameBucket = getGameBucket(id.toString());
        String gameStr = gameBucket.get();
        if (gameStr == null){
            return gameRepository.findById(id).orElseThrow(() -> new RuntimeException("game not found"));
        }
        return gson.fromJson(gameStr, Game.class);
    }

    @Override
    public List<Game> getAll() {
        return gameRepository.findAll();
    }

    private RBucket<String> getGameBucket(String gameId) {
        return redissonClient.getBucket("games::" + gameId);
    }
}
