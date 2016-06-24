package com.przemekwosko.verbs.model;

import com.przemekwosko.verbs.model.Conjugations.*;
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
    VerbConditionalPerfect verbConditionalPerfect;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    VerbConditionalPerfectProgressive verbConditionalPerfectProgressive;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    VerbConditionalPresent verbConditionalPresent;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    VerbConditionalPresentProgressive verbConditionalPresentProgressive;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    VerbFuture verbFuture;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    VerbFutureContinuous verbFutureContinuous;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    VerbFuturePerfect verbFuturePerfect;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    VerbFuturePerfetContinuous verbFuturePerfetContinuous;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    VerbPastContinuous verbPastContinuous;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    VerbPastPerfect verbPastPerfect;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    VerbPastPerfectSubjunctive verbPastPerfectSubjunctive;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    VerbPastPerfetContinuous verbPastPerfetContinuous;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    VerbPresent verbPresent;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    VerbPresentContinuous verbPresentContinuous;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    VerbPresentPerfect verbPresentPerfect;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    VerbPresentPerfectContinuous verbPresentPerfectContinuous;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    VerbPresentSubjunctive verbPresentSubjunctive;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    VerbSimplePast verbSimplePast;

    public VerbPastSubjunctive getVerbPastSubjunctive() {
        return verbPastSubjunctive;
    }

    public void setVerbPastSubjunctive(VerbPastSubjunctive verbPastSubjunctive) {
        this.verbPastSubjunctive = verbPastSubjunctive;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    VerbPastSubjunctive verbPastSubjunctive;

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

    public VerbConditionalPerfect getVerbConditionalPerfect() {
        return verbConditionalPerfect;
    }

    public void setVerbConditionalPerfect(VerbConditionalPerfect verbConditionalPerfect) {
        this.verbConditionalPerfect = verbConditionalPerfect;
    }

    public VerbConditionalPerfectProgressive getVerbConditionalPerfectProgressive() {
        return verbConditionalPerfectProgressive;
    }

    public void setVerbConditionalPerfectProgressive(VerbConditionalPerfectProgressive verbConditionalPerfectProgressive) {
        this.verbConditionalPerfectProgressive = verbConditionalPerfectProgressive;
    }

    public VerbConditionalPresent getVerbConditionalPresent() {
        return verbConditionalPresent;
    }

    public void setVerbConditionalPresent(VerbConditionalPresent verbConditionalPresent) {
        this.verbConditionalPresent = verbConditionalPresent;
    }

    public VerbConditionalPresentProgressive getVerbConditionalPresentProgressive() {
        return verbConditionalPresentProgressive;
    }

    public void setVerbConditionalPresentProgressive(VerbConditionalPresentProgressive verbConditionalPresentProgressive) {
        this.verbConditionalPresentProgressive = verbConditionalPresentProgressive;
    }

    public VerbFuture getVerbFuture() {
        return verbFuture;
    }

    public void setVerbFuture(VerbFuture verbFuture) {
        this.verbFuture = verbFuture;
    }

    public VerbFutureContinuous getVerbFutureContinuous() {
        return verbFutureContinuous;
    }

    public void setVerbFutureContinuous(VerbFutureContinuous verbFutureContinuous) {
        this.verbFutureContinuous = verbFutureContinuous;
    }

    public VerbFuturePerfect getVerbFuturePerfect() {
        return verbFuturePerfect;
    }

    public void setVerbFuturePerfect(VerbFuturePerfect verbFuturePerfect) {
        this.verbFuturePerfect = verbFuturePerfect;
    }

    public VerbFuturePerfetContinuous getVerbFuturePerfetContinuous() {
        return verbFuturePerfetContinuous;
    }

    public void setVerbFuturePerfetContinuous(VerbFuturePerfetContinuous verbFuturePerfetContinuous) {
        this.verbFuturePerfetContinuous = verbFuturePerfetContinuous;
    }

    public VerbPastContinuous getVerbPastContinuous() {
        return verbPastContinuous;
    }

    public void setVerbPastContinuous(VerbPastContinuous verbPastContinuous) {
        this.verbPastContinuous = verbPastContinuous;
    }

    public VerbPastPerfect getVerbPastPerfect() {
        return verbPastPerfect;
    }

    public void setVerbPastPerfect(VerbPastPerfect verbPastPerfect) {
        this.verbPastPerfect = verbPastPerfect;
    }

    public VerbPastPerfectSubjunctive getVerbPastPerfectSubjunctive() {
        return verbPastPerfectSubjunctive;
    }

    public void setVerbPastPerfectSubjunctive(VerbPastPerfectSubjunctive verbPastPerfectSubjunctive) {
        this.verbPastPerfectSubjunctive = verbPastPerfectSubjunctive;
    }

    public VerbPastPerfetContinuous getVerbPastPerfetContinuous() {
        return verbPastPerfetContinuous;
    }

    public void setVerbPastPerfetContinuous(VerbPastPerfetContinuous verbPastPerfetContinuous) {
        this.verbPastPerfetContinuous = verbPastPerfetContinuous;
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

    public VerbPresentPerfect getVerbPresentPerfect() {
        return verbPresentPerfect;
    }

    public void setVerbPresentPerfect(VerbPresentPerfect verbPresentPerfect) {
        this.verbPresentPerfect = verbPresentPerfect;
    }

    public VerbPresentPerfectContinuous getVerbPresentPerfectContinuous() {
        return verbPresentPerfectContinuous;
    }

    public void setVerbPresentPerfectContinuous(VerbPresentPerfectContinuous verbPresentPerfectContinuous) {
        this.verbPresentPerfectContinuous = verbPresentPerfectContinuous;
    }

    public VerbPresentSubjunctive getVerbPresentSubjunctive() {
        return verbPresentSubjunctive;
    }

    public void setVerbPresentSubjunctive(VerbPresentSubjunctive verbPresentSubjunctive) {
        this.verbPresentSubjunctive = verbPresentSubjunctive;
    }

    public VerbSimplePast getVerbSimplePast() {
        return verbSimplePast;
    }

    public void setVerbSimplePast(VerbSimplePast verbSimplePast) {
        this.verbSimplePast = verbSimplePast;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Verb verb = (Verb) o;

        if (verbbId != null ? !verbbId.equals(verb.verbbId) : verb.verbbId != null) return false;
        if (baseVerb != null ? !baseVerb.equals(verb.baseVerb) : verb.baseVerb != null) return false;
        if (translations != null ? !translations.equals(verb.translations) : verb.translations != null) return false;
        if (verbConditionalPerfect != null ? !verbConditionalPerfect.equals(verb.verbConditionalPerfect) : verb.verbConditionalPerfect != null)
            return false;
        if (verbConditionalPerfectProgressive != null ? !verbConditionalPerfectProgressive.equals(verb.verbConditionalPerfectProgressive) : verb.verbConditionalPerfectProgressive != null)
            return false;
        if (verbConditionalPresent != null ? !verbConditionalPresent.equals(verb.verbConditionalPresent) : verb.verbConditionalPresent != null)
            return false;
        if (verbConditionalPresentProgressive != null ? !verbConditionalPresentProgressive.equals(verb.verbConditionalPresentProgressive) : verb.verbConditionalPresentProgressive != null)
            return false;
        if (verbFuture != null ? !verbFuture.equals(verb.verbFuture) : verb.verbFuture != null) return false;
        if (verbFutureContinuous != null ? !verbFutureContinuous.equals(verb.verbFutureContinuous) : verb.verbFutureContinuous != null)
            return false;
        if (verbFuturePerfect != null ? !verbFuturePerfect.equals(verb.verbFuturePerfect) : verb.verbFuturePerfect != null)
            return false;
        if (verbFuturePerfetContinuous != null ? !verbFuturePerfetContinuous.equals(verb.verbFuturePerfetContinuous) : verb.verbFuturePerfetContinuous != null)
            return false;
        if (verbPastContinuous != null ? !verbPastContinuous.equals(verb.verbPastContinuous) : verb.verbPastContinuous != null)
            return false;
        if (verbPastPerfect != null ? !verbPastPerfect.equals(verb.verbPastPerfect) : verb.verbPastPerfect != null)
            return false;
        if (verbPastPerfectSubjunctive != null ? !verbPastPerfectSubjunctive.equals(verb.verbPastPerfectSubjunctive) : verb.verbPastPerfectSubjunctive != null)
            return false;
        if (verbPastPerfetContinuous != null ? !verbPastPerfetContinuous.equals(verb.verbPastPerfetContinuous) : verb.verbPastPerfetContinuous != null)
            return false;
        if (verbPresent != null ? !verbPresent.equals(verb.verbPresent) : verb.verbPresent != null) return false;
        if (verbPresentContinuous != null ? !verbPresentContinuous.equals(verb.verbPresentContinuous) : verb.verbPresentContinuous != null)
            return false;
        if (verbPresentPerfect != null ? !verbPresentPerfect.equals(verb.verbPresentPerfect) : verb.verbPresentPerfect != null)
            return false;
        if (verbPresentPerfectContinuous != null ? !verbPresentPerfectContinuous.equals(verb.verbPresentPerfectContinuous) : verb.verbPresentPerfectContinuous != null)
            return false;
        if (verbPresentSubjunctive != null ? !verbPresentSubjunctive.equals(verb.verbPresentSubjunctive) : verb.verbPresentSubjunctive != null)
            return false;
        return verbSimplePast != null ? verbSimplePast.equals(verb.verbSimplePast) : verb.verbSimplePast == null;

    }

    @Override
    public int hashCode() {
        int result = verbbId != null ? verbbId.hashCode() : 0;
        result = 31 * result + (baseVerb != null ? baseVerb.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getBaseVerb();
    }
}
