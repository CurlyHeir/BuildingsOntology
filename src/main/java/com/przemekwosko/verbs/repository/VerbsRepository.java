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

//
//    public boolean isVerbExist(String verb) {
//
//            Verb foundVerb = (Verb) session.createCriteria(Verb.class).add( Restrictions.eq("baseVerb", verb)).uniqueResult();
//            if (foundVerb != null)
//                return true;
//        return false;
//    }

    public void saveVerbs(ArrayList<Verb> verbs) {
        saveVerbToDatabase(new Verb());
//        for (Verb v : verbs) {
//            saveVerbToDatabase(v);
//        }

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
//        org.hibernate.SessionFactory sessionFactory = new SessionFactory().getConfiguration().buildSessionFactory();
//
//        StatelessSession session = sessionFactory.openStatelessSession();
//        Transaction tx = session.beginTransaction();
//        for(Verb v : verbs) {
//            session.save
//        }
//        tx.commit();
//        session.close();
    }
    public void saveVerbToDatabase(Verb verb) {
        Session mySession = SessionFactory.getSession();
        mySession.saveOrUpdate(verb);
        mySession.beginTransaction().commit();
        mySession.flush();
    }
}
