package com.destroyedlife.dateingappbackend.http.resource;

import com.destroyedlife.dateingappbackend.entity.User;
import com.destroyedlife.dateingappbackend.http.controller.UserController;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class UserResource extends EntityModel<User> {
    public UserResource(User user) {
        super(user);
        add(linkTo(UserController.class).slash(user.getId()).withSelfRel());
    }
}
