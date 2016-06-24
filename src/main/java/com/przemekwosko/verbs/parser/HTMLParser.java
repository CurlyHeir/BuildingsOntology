package com.przemekwosko.verbs.parser;

import com.przemekwosko.verbs.model.Conjugations.*;
import com.przemekwosko.verbs.model.Verb;
import com.przemekwosko.verbs.model.VerbConjugation;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by kudlaty on 17/06/16.
 */
public class HTMLParser {

    private VerbTypeFactory factory;

    public HTMLParser(VerbTypeFactory factory) {
        this.factory = factory;
    }

    public Verb parseTableToVerb(Document htmlDocument) {
        Verb verb = new Verb();
        try {
            Elements tensesTables = htmlDocument.select("div.span4.result-left.babMobPadding");



            verb.setVerbPresent((VerbPresent) getModelFromElement(tensesTables.get(0)));
            verb.setVerbPresentContinuous((VerbPresentContinuous) getModelFromElement(tensesTables.get(1)));
            verb.setVerbSimplePast((VerbSimplePast) getModelFromElement(tensesTables.get(2)));

            verb.setVerbPastContinuous((VerbPastContinuous) getModelFromElement(tensesTables.get(3)));
            verb.setVerbPresentPerfect((VerbPresentPerfect) getModelFromElement(tensesTables.get(4)));
            verb.setVerbPresentPerfectContinuous((VerbPresentPerfectContinuous) getModelFromElement(tensesTables.get(5)));

            verb.setVerbPastPerfect((VerbPastPerfect) getModelFromElement(tensesTables.get(6)));
            verb.setVerbPastPerfetContinuous((VerbPastPerfetContinuous) getModelFromElement(tensesTables.get(7)));
            verb.setVerbFuture((VerbFuture) getModelFromElement(tensesTables.get(8)));

            verb.setVerbFutureContinuous((VerbFutureContinuous) getModelFromElement(tensesTables.get(9)));
            verb.setVerbFuturePerfect((VerbFuturePerfect) getModelFromElement(tensesTables.get(10)));
            verb.setVerbFuturePerfetContinuous((VerbFuturePerfetContinuous) getModelFromElement(tensesTables.get(11)));

            verb.setVerbConditionalPresent((VerbConditionalPresent) getModelFromElement(tensesTables.get(12)));
            verb.setVerbConditionalPerfect((VerbConditionalPerfect) getModelFromElement(tensesTables.get(13)));
            verb.setVerbConditionalPresentProgressive((VerbConditionalPresentProgressive) getModelFromElement(tensesTables.get(14)));

            verb.setVerbConditionalPerfectProgressive((VerbConditionalPerfectProgressive) getModelFromElement(tensesTables.get(15)));
            verb.setVerbPresentSubjunctive((VerbPresentSubjunctive) getModelFromElement(tensesTables.get(16)));
            verb.setVerbPastSubjunctive((VerbPastSubjunctive) getModelFromElement(tensesTables.get(17)));

            verb.setVerbPastPerfectSubjunctive((VerbPastPerfectSubjunctive) getModelFromElement(tensesTables.get(18)));

            verb.setBaseVerb(verb.getVerbPresent().getPlural1());
        } catch (Exception e) {
//            e.printStackTrace();;
        }

        return verb;
    }

    private VerbConjugation getModelFromElement(Element element) {
        Elements elements = element.select("td");
//        System.out.print(elements.get(0).child(0).text());
        VerbConjugation verbConjugation;
        String tense = elements.get(0).select("b").text();
        verbConjugation = this.factory.verbConjugationForKey(tense);
        if (verbConjugation != null) {
            verbConjugation.setSingular1(elements.get(2).select("td").text());
            verbConjugation.setSingular2(elements.get(4).select("td").text());
            verbConjugation.setSingular3(elements.get(6).select("td").text());
            verbConjugation.setPlural1(elements.get(8).select("td").text());
            verbConjugation.setPlural2(elements.get(10).select("td").text());
            verbConjugation.setPlural3(elements.get(12).select("td").text());
        }

        return verbConjugation;
    }


}