package org.campusmolndal;

import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MongoDB {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    //Konstruktor
    public MongoDB(String uri, String dbName, String collectionName) {
        mongoClient = MongoClients.create(uri);
        database = mongoClient.getDatabase(dbName);
        collection = database.getCollection(collectionName);
    }
    public void createTodo(Todo todo) {
        try {
            // Genererar Objektid
            ObjectId id = new ObjectId();
            todo.setId(id.toString());
            Document doc = todo.toDoc();
            collection.insertOne(doc);
        } catch (Exception e) {

        }
    }
    public Optional<Todo> read(String id) {
        Document query = new Document("_id", new Document("$eq", id));
        Document doc = collection.find(query).first();
        if (doc != null) {
            return Optional.of(Todo.fromDoc(doc));
        }
        return Optional.empty();
    }
    public List<Todo> readAllTodo() {
        List<Todo> todos = new ArrayList<>();
        for (Document doc : collection.find()) {
            todos.add(Todo.fromDoc(doc));
        }
        return todos;
    }
    public void updateTodo(Todo todo) {
        Document query = new Document("_id", new Document("$eq", todo.getId()));
        Document update = new Document("$set", todo.toDoc());
        collection.updateOne(query, update);
    }
    public void deleteTodo(String id) {
        Document query = new Document("_id", new Document("$eq", id));
        collection.deleteOne(query);
    }
    public void closeTodo() {
        mongoClient.close();
    }
    public void deleteAllTodos() {
        collection.deleteMany(new Document());
    }

} // Slut på class





