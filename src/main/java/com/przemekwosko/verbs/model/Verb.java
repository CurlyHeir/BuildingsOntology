package com.przemekwosko.verbs.model;

import com.przemekwosko.verbs.model.Conjugations.VerbPresent;
import com.przemekwosko.verbs.model.Conjugations.VerbPresentContinuous;
import com.przemekwosko.verbs.model.Conjugations.VerbSimplePast;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Verb implements Serializable {

     @Id
     @GeneratedValue
     private Long verbbId;
     @Column(unique = true)
     private String baseVerb;
     @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
     @Fetch(FetchMode.JOIN)
     private Set<Translation> translations = new HashSet<>();

     @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
     private VerbPresent verbPresent;

     @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
     private VerbPresentContinuous verbPresentContinuous;

     @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
     private VerbSimplePast verbSimplePast;

    public Verb() {
    }

    public Long getVerbbId() {
        return verbbId;
    }

    public void setVerbbId(Long verbbId) {
        this.verbbId = verbbId;
    }

    public String getBaseVerb() {
        return baseVerb;
    }

    public void setBaseVerb(String baseVerb) {
        this.baseVerb = baseVerb;
    }

    public Set<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(Set<Translation> translations) {
        this.translations = translations;
    }

    public VerbPresent getVerbPresent() {
        return verbPresent;
    }

    public void setVerbPresent(VerbPresent verbPresent) {
        this.verbPresent = verbPresent;
    }

    public VerbPresentContinuous getVerbPresentContinuous() {
        return verbPresentContinuous;
    }

    public void setVerbPresentContinuous(VerbPresentContinuous verbPresentContinuous) {
        this.verbPresentContinuous = verbPresentContinuous;
    }

    public VerbSimplePast getVerbSimplePast() {
        return verbSimplePast;
    }

    public void setVerbSimplePast(VerbSimplePast verbSimplePast) {
        this.verbSimplePast = verbSimplePast;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
