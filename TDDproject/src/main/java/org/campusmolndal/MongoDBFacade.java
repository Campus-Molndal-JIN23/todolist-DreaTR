package org.campusmolndal;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;

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
    public void createIndex(){

        collectionA.createIndex(new Document("text", 1), new IndexOptions().unique(true));

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
    //Söker efter att-göra uppgifter i databasen
    public Todo FindById(String id) {
        Document docA = new Document("id", id);
        Document search = collectionA.find(docA).first();
        return Todo.fromDoc(search);
    }

    // Raderar att-göra uppgifter i databasen
    public void Delete(String id) {
        Document doc = new Document("id", id);
        collectionA.deleteOne(doc);
    }

    // Arraylista för att-göra uppgifter
    public ArrayList<Todo> FindTodo(String text) {
        Document docA = new Document("Text", text);
        FindIterable<Document> result = collectionA.find(docA);
        ArrayList<Todo> todoList = new ArrayList<>();
        result.forEach(todo -> todoList.add(Todo.fromDoc(todo)));

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
