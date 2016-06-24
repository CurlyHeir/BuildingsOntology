package com.przemekwosko.verbs.model.Conjugations;

import com.przemekwosko.verbs.model.VerbConjugation;

import javax.persistence.Entity;

/**
 * Created by kudlaty on 16/06/16.
 */

@Entity
public class VerbPastSubjunctive extends VerbConjugation {

    @Override
    public String tenseName() {
        return "Past Subjunctive";
    }
}
