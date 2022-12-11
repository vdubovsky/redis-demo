package io.vdubovsky.redisdemo.repository;

import io.vdubovsky.redisdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
