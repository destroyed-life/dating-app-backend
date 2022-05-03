package com.destroyedlife.dateingappbackend.http.controller;

import com.destroyedlife.dateingappbackend.entity.User;
import com.destroyedlife.dateingappbackend.http.request.UserCreationRequest;
import com.destroyedlife.dateingappbackend.http.request.validator.UserCreationValidator;
import com.destroyedlife.dateingappbackend.http.resource.ErrorsResponse;
import com.destroyedlife.dateingappbackend.http.resource.UserResource;
import com.destroyedlife.dateingappbackend.http.response.ErrorResponse;
import com.destroyedlife.dateingappbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequiredArgsConstructor
@RequestMapping(value = "/user", produces = "application/hal+json;charset=UTF-8")
@RestController
public class UserController {
    private final UserCreationValidator userCreationValidator;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @PostMapping("/signUp")
    public ResponseEntity createUser(@RequestBody @Valid UserCreationRequest userCreationRequest, Errors errors) {
        if (errors.hasErrors()) {
            return badRequest(errors);
        }
        userCreationValidator.validate(userCreationRequest, errors);
        if (errors.hasErrors()) {
            return badRequest(errors);
        }

        User user = modelMapper.map(userCreationRequest, User.class);
        User newUser = userRepository.save(user);
        WebMvcLinkBuilder selfLinkBuilder = linkTo(UserController.class).slash(newUser.getId());
        URI createdUri = selfLinkBuilder.toUri();

        UserResource userResource = new UserResource(user);
        userResource.add(linkTo(UserController.class).withRel("query-user"));
        userResource.add(selfLinkBuilder.withRel("update-user"));
        userResource.add(Link.of("/docs/index.html#resources").withRel("profile"));

        return ResponseEntity.created(createdUri).body(userResource);
    }

    private ResponseEntity badRequest(Errors errors) {
        return ResponseEntity.badRequest().body(new ErrorsResponse(errors, null));
    }
}
