//package com.filipcygan.buidlingsOntology.service;
//
//import com.filipcygan.buidlingsOntology.model.Building;
//import com.filipcygan.buidlingsOntology.model.SessionFactory;
//import com.filipcygan.buidlingsOntology.model.Type;
//import javafx.concurrent.ScheduledService;
//import javafx.concurrent.Task;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.util.ArrayList;
//
//public class FillDatabaseService extends ScheduledService {
//    @Override
//    protected Task createTask() {
//
//        return new Task() {
//
//            @Override
//            protected Integer call() throws Exception {
////                System.out.println("Zaczynamy");
//                final Session session = SessionFactory.getSession();
//                String JSONAsString = getStringFromURL("http://dbpedia" +
//                                                       ".org/sparql?default-graph-uri=http%3A%2F%2Fdbpedia" +
//                                                       ".org&query=SELECT+%3Fname+%3Fsubject+%7B%7B%0D%0ASELECT+%3Fname+%3Fsubject%0D%0A%7B%0D%0A%3Fbuilding+rdfs%3Alabel+%3Fname++.%0D%0A++%3Fbuilding+a+dbo%3AArchitecturalStructure+.%0D%0A%3Fbuilding+dct%3Asubject+%3Fsubject%0D%0Afilter+langMatches%28+lang%28%3Fname%29%2C+%22en%22+%29%0D%0A%7D%0D%0Aorder+by+%3Fbuilding%0D%0A%7D%7D%0D%0Aoffset+0%0D%0ALIMIT+1000&format=application%2Fsparql-results%2Bjson&CXML_redir_for_subjs=121&CXML_redir_for_hrefs=&timeout=30000&debug=on");
////                System.out.println("mamy stringa");
//                JSONObject jsonObject = new JSONObject(JSONAsString);
////                System.out.println("mamy jsonobject");
//                JSONArray bindings = jsonObject.getJSONObject("results").getJSONArray("bindings");
////                System.out.println("mamy array json");
//
//                ArrayList<Building> buildingList = new ArrayList<Building>();
//
//                for (int i = 0; i < bindings.length(); i++) {
//                    Building building = new Building();
//                    Type type = new Type();
//                    building.setName(bindings.getJSONObject(i).getJSONObject("name").getString("value"));
////                    List<Type> types = new ArrayList<Type>();
//                    building.setType(bindings.getJSONObject(i).getJSONObject("subject").getString("value"));
////                    types.add(type);
////                    building.setType(types);
////                    System.out.println(building.getName() + building.getType());
//                    session.save(building);
////                    System.out.println(i + "   " + building.getName());
//                }
//                try {
//                    Transaction tx;
//                    tx = session.beginTransaction();
//
//
//                    tx.commit();
//
//                } finally {
//                    session.close();
//                }
//                System.out.println(bindings.getJSONObject(1).getJSONObject("name").getString("value"));
//                System.out.println("Dodano do bazy");
//                return null;
//            }
//        };
//    }
//
//    private String getStringFromURL(String urlString) throws Exception {
//
//        BufferedReader reader = null;
//        try {
//            URL url = new URL(urlString);
//            reader = new BufferedReader(new InputStreamReader(url.openStream()));
//            StringBuffer buffer = new StringBuffer();
//            int read;
//            char[] chars = new char[1024];
//            while ((read = reader.read(chars)) != -1)
//                buffer.append(chars, 0, read);
//
//            return buffer.toString();
//        } finally {
//            if (reader != null)
//                reader.close();
//        }
//    }
//}
//
