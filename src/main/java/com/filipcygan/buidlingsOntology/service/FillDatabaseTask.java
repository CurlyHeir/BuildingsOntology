package com.filipcygan.buidlingsOntology.service;

import com.filipcygan.buidlingsOntology.model.Building;
import com.filipcygan.buidlingsOntology.model.SessionFactory;
import com.filipcygan.buidlingsOntology.model.Type;
import javafx.concurrent.Task;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class FillDatabaseTask extends Task {
    @Override
    protected Integer call() throws Exception {
        final Session session = SessionFactory.getSession();
        for (int offset = 0; ; offset += 1000) {
            String JSONAsString = getStringFromURL("http://dbpedia" +
                                                   ".org/sparql?default-graph-uri=http%3A%2F%2Fdbpedia" +
                                                   ".org&query=SELECT+%3Fname+%3Fsubject+%7B%7B%0D%0ASELECT+%3Fn" +
                                                   "ame+%3Fsubject%0D%0A%7B%0D%0A%3Fbuilding+rdfs%3Alabel+%3Fname++.%0D" +
                                                   "%0A++%3Fbuilding+a+dbo%3AArchitecturalStructure+.%0D%0A%3Fbuilding+d" +
                                                   "ct%3Asubject+%3Fsubject%0D%0Afilter+langMatches%28+lang%28%3Fname%2" +
                                                   "9%2C+%22pl%22+%29%0D%0A%7D%0D%0Aorder+by+%3Fbuilding%0D%0A%7D%7D%0D%0Aoffset+" +
                                                   offset +
                                                   "%0D%0ALIMIT+1000&format=application%2Fsparql-results%2Bjson&CXML_redi" +
                                                   "r_for_subjs=121&CXML_redir_for_hrefs=&timeout=30000&debug=on");
            JSONObject jsonObject = new JSONObject(JSONAsString);
            JSONArray bindings = jsonObject.getJSONObject("results").getJSONArray("bindings");
            if (bindings.isNull(0))
                break;

            for (int i = 0; i < bindings.length(); i++) {
                Building building = new Building();

                String name = bindings.getJSONObject(i).getJSONObject("name").getString("value");
                Building aaa = (Building) session.createCriteria(Building.class)
                                                 .add(Restrictions.eq("buildingName", name))
                                                 .uniqueResult();
                if (aaa != null)
                    building = aaa;
                else
                    building.setBuildingName(name);
                String typeName = bindings.getJSONObject(i).getJSONObject("subject").getString("value").substring(37).
                replace('_',
                                                                                                                ' ');
                Type type = (Type) session.createCriteria(Type.class)
                                          .add(Restrictions.eq("typeName", typeName))
                                          .uniqueResult();
                if (type != null)
                    building.addType(type);
                else
                    building.addType(new Type(typeName));
                session.saveOrUpdate(building);
                session.beginTransaction().commit();
            }
        }
        System.out.println("Dodano do bazy");

        return null;

    }


    private String getStringFromURL(String urlString) throws Exception {

        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder buffer = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}

