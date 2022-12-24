package io.vdubovsky.redisdemo.controller;

import io.vdubovsky.redisdemo.model.Game;
import io.vdubovsky.redisdemo.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/games")
public class GameController {

    private final GameService gameService;

    @GetMapping("/{id}")
    public Game findById(@PathVariable UUID id){
        return gameService.getById(id);
    }

    @GetMapping
    public List<Game> findAll(){
        return gameService.getAll();
    }

    @PostMapping
    public Game create(@RequestBody Game game){
        try {
            return gameService.create(game);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
