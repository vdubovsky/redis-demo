package io.vdubovsky.redisdemo.service;

import io.vdubovsky.redisdemo.model.Game;

import java.util.List;
import java.util.UUID;

public interface GameService {

    Game create(Game game) throws InterruptedException;

    Game getById(UUID id);

    List<Game> getAll();
}
