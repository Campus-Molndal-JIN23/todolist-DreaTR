package org.campusmolndal;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Todo {

    String _id;
    String text;
    boolean done;

    public Todo(String text, boolean done, String _id){

        this.text=text;
        this.done=done;
        this._id=_id;
    }
    public Todo (MongoDBFacade mongoDBFacade) {
    }

    public Todo(String text, boolean done) {
    }

    public Todo(String string, String text, boolean done) {
    }

    public String getText() {
        return text;
    }

    public boolean isDone() {
        return done;
    }

    public String getId() {
        return _id;
    }

    public void setText(String newText) {
        this.text = newText;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setId(String id) {
        this._id = id;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "text='" + text + '\'' +
                ", done=" + done +
                ", _id='" + _id + '\'' +
                '}';
    }
    public static Todo fromDoc(Document doc) {
        ObjectId id = doc.getObjectId("_id");
        String text = doc.getString("text");
        boolean done = Boolean.valueOf(doc.getBoolean("done"));
        return new Todo(text, done, id.toString());
    }
    public Document toDoc() {
        return new Document("text", text)
                .append("done", done);
    }

}
