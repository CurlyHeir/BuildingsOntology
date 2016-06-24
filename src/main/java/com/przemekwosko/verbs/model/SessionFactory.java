package com.przemekwosko.verbs.model;

import com.przemekwosko.verbs.model.Conjugations.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Class needed for Hibernate that sets SessionFactory from hibernate.cfg.xml
 */
public class SessionFactory {
    private static final org.hibernate.SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = getConfiguration();

            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Method for retrieving session
     * @return session
     * @throws HibernateException
     */
    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.configure();

        // verbs and conjugation
        configuration.addAnnotatedClass(Verb.class);
        configuration.addAnnotatedClass(VerbConditionalPerfect.class);
        configuration.addAnnotatedClass(VerbConditionalPerfectProgressive.class);
        configuration.addAnnotatedClass(VerbConditionalPresent.class);
        configuration.addAnnotatedClass(VerbConditionalPresentProgressive.class);
        configuration.addAnnotatedClass(VerbFuture.class);
        configuration.addAnnotatedClass(VerbFutureContinuous.class);
        configuration.addAnnotatedClass(VerbFuturePerfect.class);
        configuration.addAnnotatedClass(VerbFuturePerfetContinuous.class);
        configuration.addAnnotatedClass(VerbPastContinuous.class);
        configuration.addAnnotatedClass(VerbPastPerfect.class);
        configuration.addAnnotatedClass(VerbPastPerfetContinuous.class);
        configuration.addAnnotatedClass(VerbPastPerfectSubjunctive.class);
        configuration.addAnnotatedClass(VerbPastSubjunctive.class);
        configuration.addAnnotatedClass(VerbPresent.class);
        configuration.addAnnotatedClass(VerbPresentContinuous.class);
        configuration.addAnnotatedClass(VerbPresentPerfect.class);
        configuration.addAnnotatedClass(VerbPresentPerfectContinuous.class);
        configuration.addAnnotatedClass(VerbPresentSubjunctive.class);
        configuration.addAnnotatedClass(VerbSimplePast.class);

        // other
        configuration.addAnnotatedClass(Translation.class);
        return configuration;
    }
}
