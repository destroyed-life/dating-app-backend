package com.destroyedlife.dateingappbackend.http.resource;

import com.destroyedlife.dateingappbackend.http.controller.IndexController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.validation.Errors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ErrorsResponse extends EntityModel<Errors> {
    public ErrorsResponse(Errors content, Iterable<Link> links) {
        super(content, links);
        add(linkTo(methodOn(IndexController.class).index()).withRel("index"));
    }
}
