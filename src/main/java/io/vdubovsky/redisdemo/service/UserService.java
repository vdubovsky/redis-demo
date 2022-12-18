package io.vdubovsky.redisdemo.service;

import io.vdubovsky.redisdemo.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User create(User userDto);

    User getById(UUID uuid);
    User getByIdCacheable(UUID uuid);

    List<User> getAll();
    List<User> getAllStartFromV();
}
