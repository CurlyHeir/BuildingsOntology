package com.przemekwosko.verbs.repository;

import com.przemekwosko.verbs.model.SessionFactory;
import com.przemekwosko.verbs.model.Verb;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;

/**
 * Created by kudlaty on 23/06/16.
 */
public class VerbsRepository {

    public void saveVerbs(ArrayList<Verb> verbs) {
        saveVerbToDatabase(new Verb());

        Session session = SessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        for (int i=0; i<verbs.size(); i++) {
            session.save(verbs.get(i));
            if(i %20 == 0) {
                session.clear();
                session.flush();
            }
        }
        transaction.commit();
        session.close();
    }

    public void saveVerbToDatabase(Verb verb) {
        Session mySession = SessionFactory.getSession();
        mySession.saveOrUpdate(verb);
        mySession.beginTransaction().commit();
        mySession.flush();
    }
}
