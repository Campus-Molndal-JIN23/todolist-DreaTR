package org.campusmolndal;

import org.bson.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void testTodoCreation() {
        Todo todo = new Todo();
        assertNotNull(todo.getId());
        assertNull(todo.getText());
        assertFalse(todo.isDone());
    }
    @Test
    void testTodoCreationWithParameters() {
        String text = "Sample Todo";
        boolean done = true;
        Todo todo = new Todo(text, done);
        assertNotNull(todo.getId());
        assertEquals(text, todo.getText());
        assertEquals(done, todo.isDone());
    }
    @Test
    void testSettersAndGetters() {
        Todo todo = new Todo();
        String id = "123";
        String text = "Sample Todo";
        boolean done = true;

        todo.setId(id);
        todo.setText(text);
        todo.setDone(done);

        assertEquals(id, todo.getId());
        assertEquals(text, todo.getText());
        assertEquals(done, todo.isDone());
    }
    @Test
    void testToDoc() {
        Todo todo = new Todo();
        todo.setId("123");
        todo.setText("Sample Todo");
        todo.setDone(true);

        Document doc = todo.toDoc();

        assertEquals("123", doc.getString("_id"));
        assertEquals("Sample Todo", doc.getString("text"));
        assertTrue(doc.getBoolean("done"));
    }
    @Test
    void testFromDoc() {
        Document doc = new Document("_id", "123")
                .append("text", "Sample Todo")
                .append("done", true);

        Todo todo = Todo.fromDoc(doc);

        assertEquals("123", todo.getId());
        assertEquals("Sample Todo", todo.getText());
        assertTrue(todo.isDone());
    }
    @Test
    void testToString() {

        String id = "123";
        String text = "Sample Todo";
        boolean done = true;

        Todo todo = new Todo();
        todo.setId(id);
        todo.setText(text);
        todo.setDone(done);

        String expectedString = String.format("Todo%nID: %s%nText: %s%nStatus: Done%n", id, text);

        assertEquals(expectedString, todo.toString());

    }

}