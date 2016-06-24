package com.przemekwosko.verbs.service;

import com.przemekwosko.verbs.model.Verb;
import com.przemekwosko.verbs.parser.HTMLParser;
import com.przemekwosko.verbs.parser.VerbTypeFactory;
import com.przemekwosko.verbs.repository.VerbsRepository;
import com.przemekwosko.verbs.resources.VerbList;
import com.sun.corba.se.spi.activation.NoSuchEndPoint;
import com.sun.corba.se.spi.activation.Repository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.observables.BlockingObservable;
import rx.schedulers.Schedulers;

import java.io.IOException;
import java.util.ArrayList;
import java.lang.System.*;

import static java.lang.System.out;

/**
 * Created by kudlaty on 19/06/16.
 */
public class HTMLContentService {

    private final String serviceURL = "http://en.bab.la/conjugation/english/";
    private final VerbsRepository verbsRepository;

    public HTMLContentService() {
        verbsRepository = new VerbsRepository();
    }

    public Observable<ArrayList<Verb>> startFetch() {
        return Observable.create((Subscriber<? super ArrayList<Verb>> s) -> {
            ArrayList<Verb> verbs = new ArrayList<>();
            retrieveVerbs().subscribe(new Observer<Document>() {

                @Override
                public void onCompleted() {

                    verbsRepository.saveVerbs(verbs);
                    out.println("END!");
                }

                @Override
                public void onError(Throwable throwable) {

                }

                @Override
                public void onNext(Document document) {
                    Verb object = new HTMLParser(new VerbTypeFactory()).parseTableToVerb(document);
                    verbs.add(object);
                }
            });
            s.onNext(verbs);
            s.onCompleted();
        });
    }

    /**
     * Or the `window` operator can be used instead of buffer to process them as a stream instead of buffered list.
     */
    private Observable<Document> retrieveVerbs() {
        ArrayList<String> verbs = new VerbList().getVerbsArray();
        return Observable.from(verbs).flatMap(this::getDataAsync, 2);
    }

    private Observable<Document> getDataAsync(String verb) {
        return getDataSync(verb).subscribeOn(Schedulers.io());
    }

    private Observable<Document> getDataSync(String verb) {
        return Observable.create((Subscriber<? super Document> s) -> {
            Document doc = null;
            try {
                doc = Jsoup.connect(serviceURL + verb).timeout(5 * 1000).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            s.onNext(doc);
            s.onCompleted();
        });
    }
}
