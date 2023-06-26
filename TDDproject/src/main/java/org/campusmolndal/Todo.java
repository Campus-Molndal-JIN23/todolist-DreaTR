package org.campusmolndal;

import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.UUID;

public class Todo {

    String id;
    String text;
    boolean done;

    public Todo(){
        this.id=UUID.randomUUID().toString();
    }

    public Todo(String text, boolean done) {
        this();
        this.text=text;
        this.done=done;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
    public Document toDoc() {
        return new Document("_id", id != null ? id : "")
                .append("text", text)
                .append("done", done);
    }
    public static Todo fromDoc(Document doc) {
        Todo todo = new Todo();
        Object id = doc.get("_id");
        if (id instanceof ObjectId) {
            todo.setId(((ObjectId) id).toString());
        } else if (id instanceof String) {
            todo.setId((String) id);
        }
        todo.setText(doc.getString("text"));
        todo.setDone(doc.getBoolean("done"));
        return todo;
    }
    @Override
    public String toString() {
        String doneStatus = done ? "Done" : "Not Done";
        return String.format("Todo%nID: %s%nText: %s%nStatus: %s%n", id, text, doneStatus);
    }

}// Slut på class
