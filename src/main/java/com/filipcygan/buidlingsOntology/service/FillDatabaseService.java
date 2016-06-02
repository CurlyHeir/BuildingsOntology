package com.filipcygan.buidlingsOntology.service;

import com.filipcygan.buidlingsOntology.BuildingTypes;
import com.filipcygan.buidlingsOntology.model.Building;
import com.filipcygan.buidlingsOntology.model.SessionFactory;
import com.filipcygan.buidlingsOntology.model.Type;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class FillDatabaseService extends ScheduledService {
    @Override
    protected Task createTask() {

        return new Task() {
            @Override
            protected Integer call() throws Exception {
                final Session session = SessionFactory.getSession();
                for (int offset = 0; ; offset += 1000) {
                    String JSONAsString = getStringFromURL("http://dbpedia.org/sparql?default-graph-uri=http%3A%2F%2Fdbpedia.org&query=PREFIX+owl%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2002%2F07%2Fowl%23%3E%0D%0APREFIX+xsd%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema%23%3E%0D%0APREFIX+rdfs%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0D%0APREFIX+rdf%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0D%0APREFIX+foaf%3A+%3Chttp%3A%2F%2Fxmlns.com%2Ffoaf%2F0.1%2F%3E%0D%0APREFIX+dc%3A+%3Chttp%3A%2F%2Fpurl.org%2Fdc%2Felements%2F1.1%2F%3E%0D%0APREFIX+%3A+%3Chttp%3A%2F%2Fdbpedia.org%2Fresource%2F%3E%0D%0APREFIX+dbpedia2%3A+%3Chttp%3A%2F%2Fdbpedia.org%2Fproperty%2F%3E%0D%0APREFIX+dbpedia%3A+%3Chttp%3A%2F%2Fdbpedia.org%2F%3E%0D%0APREFIX+skos%3A+%3Chttp%3A%2F%2Fwww.w3.org%2F2004%2F02%2Fskos%2Fcore%23%3E%0D%0APREFIX+dbo%3A+%3Chttp%3A%2F%2Fdbpedia.org%2Fontology%2F%3E%0D%0A%0D%0ASELECT+%3Fname+%3Fsubject+%7B%7B%0D%0ASELECT+%3Fname+%3Fsubject%0D%0A%7B%0D%0A%3Fbuilding+rdfs%3Alabel+%3Fname++.%0D%0A++%3Fbuilding+a+dbo%3AArchitecturalStructure+.%0D%0A%3Fbuilding+rdf%3Atype+%3Fsubject%0D%0Afilter+langMatches%28+lang%28%3Fname%29%2C+%22pl%22+%29%0D%0A%7D%0D%0Aorder+by+%3Fbuilding%0D%0A%7D%7D%0D%0Aoffset+" + offset + "%0D%0ALIMIT+1000%0D%0A&format=application%2Fsparql-results%2Bjson&CXML_redir_for_subjs=121&CXML_redir_for_hrefs=&timeout=30000&debug=on");
                    JSONObject jsonObject = new JSONObject(JSONAsString);
                    JSONArray bindings = jsonObject.getJSONObject("results").getJSONArray("bindings");
                    if (bindings.isNull(0)) {
                        System.out.println("koniec");
                        break;
                    }
                    for (int i = 0; i < bindings.length(); i++) {
                        Building building = new Building();
                        String name = bindings.getJSONObject(i).getJSONObject("name").getString("value");
                        Building buildingFromDatabase = (Building) session.createCriteria(Building.class).add(Restrictions.eq
                                ("buildingName", name)).uniqueResult();
                        if (buildingFromDatabase != null) {
                            building = buildingFromDatabase;
                        } else {
                            building.setBuildingName(name);
                        }
                        String typeName = bindings.getJSONObject(i).getJSONObject("subject").getString("value");
                        for (BuildingTypes buildingType : BuildingTypes.values()) {
                            if (typeName.contains(buildingType.name())) {
                                typeName = buildingType.getName();
                                break;
                            }
                        }
                       if (typeName.contains("http")) {
                            continue;
                       }
                        Type type = (Type) session.createCriteria(Type.class).add(Restrictions.eq("typeName",
                                typeName)).uniqueResult();
                        if (type != null) {
                            building.addType(type);
                        } else {
                            building.addType(new Type(typeName));
                        }
                        session.saveOrUpdate(building);
                        session.beginTransaction().commit();
                    }
                    System.out.println(offset);
                }
                return null;
            }

            private String getStringFromURL(String urlString) throws IOException {

                BufferedReader reader = null;
                try {
                    URL url = new URL(urlString);
                    reader = new BufferedReader(new InputStreamReader(url.openStream()));
                    StringBuilder buffer = new StringBuilder();
                    int read;
                    char[] chars = new char[1024];
                    while ((read = reader.read(chars)) != -1) buffer.append(chars, 0, read);

                    return buffer.toString();
                } finally {
                    if (reader != null) reader.close();
                }
            }
        };

    }
}