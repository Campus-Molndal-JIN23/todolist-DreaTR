package org.campusmolndal;

import static com.mongodb.client.model.Filters.eq;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class MongoDBFacade {

    MongoClient client;

    MongoDatabase db;

    MongoCollection<Document> collectionA; //Kollektion för Todolistan

    String conString ="mongodb://localhost:27017";

    String databaseName= "Todo";

    String collectionName= "todoList";

    //Konstruktor

    public MongoDBFacade(String conString, String databaseName, String collectionName){

        this.conString=conString;
        this.databaseName=databaseName;
        this.collectionName=collectionName;
        Connect();

    }

    public MongoDBFacade() {
        Connect();
    }

    //Skapar index för todo kollektion
    public boolean createIndex(){

        collectionA.createIndex(new Document("text", 1), new IndexOptions().unique(true));

        return false;
    }

    //Lägger till att- göra uppgifter i databasen
    public void insertOne(Todo todoList) {
        Document docA = todoList.toDoc();
        docA.remove("_id");

        var find = collectionA.find(docA);
        if (find.first() == null) {
            collectionA.insertOne(docA);
        }
    }

    //Söker efter att- göra uppgifter i databasen
    public Document findById(String id) {
        Document query = new Document("_id", id);
        return collectionA.find(query).first();
    }
    //Uppdaterar att- göra uppgift
    public void updateOne(Todo todoToUpdate) {
        Document query = new Document("_id", todoToUpdate.getId());
        Document update = new Document("$set", new Document("text", todoToUpdate.getText()));
        collectionA.updateOne(query, update);
    }

    // Raderar att- göra uppgifter i databasen
    public void Delete(String id) {
        Document doc = new Document("id", id);
        collectionA.deleteOne(doc);
    }
    public ArrayList<Todo> getAllTodos() {

        ArrayList<Todo> todoList = new ArrayList<>();
        FindIterable<Document> result = collectionA.find();
        result.forEach(todo -> {
            ObjectId id = todo.getObjectId("_id");
            String text = todo.getString("text");
            boolean done = todo.getBoolean("done");
            Todo todoItem = new Todo(id.toString(), text, done);
            System.out.println(todoItem); // Skriv ut Todo-objektet för felsökning
            todoList.add(todoItem);
        });
        return todoList;
    }

    // Ansluter till databasen och hämtar instanser av databasen och samlingen
    public void Connect(){
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(conString))
                .serverApi(serverApi)
                .build();

        try {
            client = MongoClients.create(settings);
            db = client.getDatabase(databaseName);
            collectionA = db.getCollection(collectionName);
        } catch (Exception ex) {
            System.out.println("Oj");
            System.out.println(ex.getMessage());
        }
        try {
            createIndex();
        } catch (Exception ex) {
            System.out.println(";)");
        }

    }

}
