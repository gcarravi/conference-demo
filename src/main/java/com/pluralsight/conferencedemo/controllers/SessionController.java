package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Session> list(){
        return sessionRepository.findAll();
    }

    @GetMapping
    @RequestMapping(value="{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Session get(@PathVariable Long id){
        return sessionRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Session create(@RequestBody final Session session){
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        //Also need to look for children records before deleting.
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Session update(@PathVariable Long id, @RequestBody Session session){
        //Because this is a PUT, we expect all the attributes to be passed. If an attribute is not passed it will be replaced with a null value.
        //If you don't require (or don't want) to update all the attributes, you can choose PATCH instead of PUT

        //Todo: add validation that all attributes are passed in, otherwise return a 400  bad payload.
        Session previousSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(session, previousSession, "id");

        return sessionRepository.saveAndFlush(previousSession);
    }
}
