package com.przemekwosko.verbs.parser;

import com.przemekwosko.verbs.model.Conjugations.*;
import com.przemekwosko.verbs.model.VerbConjugation;

/**
 * Created by kudlaty on 19/06/16.
 */
public class VerbTypeFactory {

    VerbConjugation verbConjugationForKey(String key) {
        switch (key) {
            case "Present":
                return new VerbPresent();
            case "Present continuous":
                return new VerbPresentContinuous();
            case "Simple past":
                return new VerbSimplePast();
            case "Past continuous":
                return new VerbPastContinuous();
            case "Present perfect":
                return new VerbPresentPerfect();
            case "Present perfect continuous":
                return new VerbPresentPerfectContinuous();
            case "Past perfect":
                return new VerbPastPerfect();
            case "Past perfect continuous":
                return new VerbPastPerfetContinuous();
            case "Future":
                return new VerbFuture();
            case "Future continuous":
                return new VerbFutureContinuous();
            case "Future perfect":
                return new VerbFuturePerfect();
            case "Future perfect continuous":
                return new VerbFuturePerfetContinuous();
            case "Conditional present":
                return new VerbConditionalPresent();
            case "Conditional perfect":
                return new VerbConditionalPerfect();
            case "Conditional present progressive":
                return new VerbConditionalPresentProgressive();
            case "Conditional perfect progressive":
                return new VerbConditionalPerfectProgressive();
            case "Present subjunctive":
                return new VerbPresentSubjunctive();
            case "Past subjunctive":
                return new VerbPastSubjunctive();
            case "Past perfect subjunctive":
                return new VerbPastPerfectSubjunctive();
            default:
                System.out.print("VerbTypeFactory::verbConjugationForKey unnnown key passed: " + key);
                return null;
        }
    }
}
