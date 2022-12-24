package io.vdubovsky.redisdemo.repository;

import io.vdubovsky.redisdemo.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GameRepository extends JpaRepository<Game, UUID> {
}
