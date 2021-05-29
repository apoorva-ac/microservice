package com.microservice.user.controller;

import com.microservice.user.entity.User;
import com.microservice.user.service.UserService;
import com.microservice.user.vo.ResponseTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Locale;


@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService service;

    @PostMapping("/")
    public ResponseEntity saveUser(@Valid @RequestBody User user){
        log.info("inside saveUser");
        User savedUser = service.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getUserId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable Long id){
        ResponseTemplateVO rtvo= service.getUserWithDepartment(id);

        Link link=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers()).withRel("ALL-USERS");
        rtvo.add(link);

        return rtvo;
    }

    @GetMapping(value = "/all")
    public List<User> retrieveAllUsers(){
        return service.retrieveAllUsers();
    }

    @GetMapping("/test")
    public String testMessage(@RequestHeader(value = "Accept-Language", required = false) Locale locale){
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }
}
