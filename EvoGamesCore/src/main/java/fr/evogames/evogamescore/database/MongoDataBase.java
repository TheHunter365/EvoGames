package fr.evogames.evogamescore.database;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import fr.evogames.evogamesapi.database.DataBase;

public class MongoDataBase implements DataBase {

    private MongoClient mongoClient;
    private String databaseName;
    private String collectionName;
    private MongoDatabase database;
    private MongoCollection<BasicDBObject> collection;

    public MongoDataBase(String host, int port, String databaseName, String collectionName) {
        this.mongoClient = MongoClients.create("mongodb://" + host + ":" + port);
        this.databaseName = databaseName;
        this.collectionName = collectionName;
        this.database = this.mongoClient.getDatabase(this.databaseName);
        this.collection = this.database.getCollection(this.collectionName, BasicDBObject.class);
    }

    public void set(String key, String value) {
        BasicDBObject object = new BasicDBObject().append(key, value);
        this.collection.insertOne(object);
    }

    public String get(String key) {
        return this.collection.find(Filters.eq(key)).first().get("value").toString();
    }
}
