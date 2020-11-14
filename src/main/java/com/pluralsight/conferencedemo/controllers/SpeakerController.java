package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Speaker;
import com.pluralsight.conferencedemo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakerController {

    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Speaker> list(){
        return speakerRepository.findAll();
    }

    @GetMapping
    @RequestMapping(value="{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Speaker get(@PathVariable Long id){
        return speakerRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Speaker create(@RequestBody final Speaker speaker){
        return speakerRepository.saveAndFlush(speaker);
    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        //Also need to look for children records before deleting.
        speakerRepository.deleteById(id);
    }

    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker){

        Speaker previousSpeaker = speakerRepository.getOne(id);
        BeanUtils.copyProperties(speaker, previousSpeaker, "id");

        return speakerRepository.saveAndFlush(previousSpeaker);
    }
}
