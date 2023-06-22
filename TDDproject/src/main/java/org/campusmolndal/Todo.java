package org.campusmolndal;

import org.bson.Document;

public class Todo {

    String _id;
    String text;
    String done;

    Todo(String text, String done, String _id){

        this.text=text;
        this.done=done;
        this._id=_id;
    }
    public Todo (MongoDBFacade mongoDBFacade)
    {

    }
    public String getText(){
        return text;
    }
    public String getDone(){
        return done;
    }
    public String get_id(){
        return _id;
    }
    @Override
    public String toString (){
        return text + done + _id;
    }
    public static Todo fromDoc(Document doc) {
        if (doc == null) {
            return new Todo("", "", "");
        }
        return new Todo(
                doc.getString("text"),
                doc.getString("done"),
                doc.getString("id")
        );
    }
    public Document toDoc() {
        return new Document("text", text)
                .append("done", done)
                .append("_id",_id);

    }

    public void setText(String newText) {
        this.text = newText;

    }
    public void setDone(boolean newDoneStatus) {
        this.done = String.valueOf(newDoneStatus);

    }

    public boolean isDone() {
        return Boolean.parseBoolean(done);

    }

    public void set_id(String number) {
        this._id=number;
    }
}
