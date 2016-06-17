package com.przemekwosko.verbs;

import com.przemekwosko.verbs.model.Conjugations.VerbPresent;
import com.przemekwosko.verbs.model.SessionFactory;
import com.przemekwosko.verbs.model.Translation;
import com.przemekwosko.verbs.model.Verb;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kudlaty on 16/06/16.
 */
public class MainTest {

    public static void main(String[] string) {

//        Verb verb = new Verb();
//        Set<Translation> translations = new HashSet<>();
//        Translation translation = new Translation();
//        translation.setTranslation("dupa");
//
//        translations.add(translation);
//
//        verb.setTranslations(translations);
//
//        verb.setBaseVerb("baseVerb");
//        VerbPresent present = new VerbPresent();
//        present.setPlural1("verb present pural 1")``;
//        present.setPlural2("verb plural 2");
//        verb.setVerbPresent(present);
//
//        try (Session session = SessionFactory.getSession()) {
//            session.saveOrUpdate(verb);
//            session.beginTransaction().commit();
//        }
        Verb verb1 = null;

        try (Session session = com.przemekwosko.verbs.model.SessionFactory.getSession()) {
            verb1 = (Verb) session.createCriteria(Verb.class).list().get(0);
            System.out.println("verb");
        } catch (Exception e) {

        }

    }
}
