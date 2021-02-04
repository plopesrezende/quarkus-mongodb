package org.acme.mongodb.panache;


import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.eq;

@ApplicationScoped
public class FruitService {

    @Inject
    MongoClient mongoClient;

    public List<Fruit> list() {
        List<Fruit> list = new ArrayList<>();
        //return getCollection().find().limit(100000).toString();
        
        try (MongoCursor<Document> cursor = getCollection().find().limit(100000).iterator()) {

            while (cursor.hasNext()) {
              Document document = cursor.next();
              Fruit fruit = new Fruit();
              fruit.setName(document.getString("name"));
              //fruit.setDescription(document.getString("description"));
              list.add(fruit);
            }
        }
//        MongoCursor<Document> cursor = getCollection().find().limit(100000).iterator();
//
//        try {
//            while (cursor.hasNext()) {
//                Document document = cursor.next();
//                Fruit fruit = new Fruit();
//                fruit.setName(document.getString("name"));
//                //fruit.setDescription(document.getString("description"));
//                list.add(fruit);
//            }
//        } finally {
//            cursor.close();
//        }
        return list;
    }
    


    public Fruit get(String id) {
    	Bson bson = new BasicDBObject("_id", id);
        Document document = getCollection().find(bson).first();

        Fruit fruit = new Fruit();
        fruit.setId(document.getString("_id"));
        fruit.setName(document.getString("name"));
        //fruit.setDescription(document.getString("description"));
        return fruit;
    }

    public void add(Fruit fruit) {
        Document document = new Document()
                .append("name", fruit.getName());
                //.append("description", fruit.getDescription());
        getCollection().insertOne(document);
    }

    private MongoCollection<Document> getCollection() {
        return mongoClient.getDatabase("persons").getCollection("ThePerson");
    }
}
