package com.pluralsight.conferencedemo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity(name = "sessions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="session_id", insertable=false, updatable=false)
    private Long session_id;

//    @Column(name="session_name")
    private String session_name;
//    @Column(name="session_description")
    private String session_description;
//    @Column(name="session_length")
    private String session_length;

    @ManyToMany
    @JoinTable(name="session_speakers",
                joinColumns = @JoinColumn(name="session_id"),
                inverseJoinColumns = @JoinColumn(name="speaker_id"))
    private List<Speaker> speakers;

    public Session() {
    }

    public Long getSession_id() {
        return session_id;
    }

    public void setSession_id(Long session_id) {
        this.session_id = session_id;
    }

    public String getSession_name() {
        return session_name;
    }

    public void setSession_name(String session_name) {
        this.session_name = session_name;
    }

    public String getSession_description() {
        return session_description;
    }

    public void setSession_description(String session_description) {
        this.session_description = session_description;
    }

    public String getSession_length() {
        return session_length;
    }

    public void setSession_length(String session_length) {
        this.session_length = session_length;
    }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }
}
