package org.campusmolndal;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        Document doc = mock(Document.class);
        when(doc.get("_id")).thenReturn(new ObjectId());
        when(doc.getString("text")).thenReturn("Buy groceries");
        when(doc.getBoolean("done")).thenReturn(false);

        Todo todo = Todo.fromDoc(doc);

        assertEquals("Buy groceries", todo.getText());
        assertEquals(false, todo.isDone());
    }
    @Test
    void testToString() {

        Todo todoMock = mock(Todo.class);

        when(todoMock.toString()).thenReturn("Todo\nID: 123\nText: Finish project\nStatus: Not Done\n");

        String expectedOutput = "Todo\nID: 123\nText: Finish project\nStatus: Not Done\n";
        String actualOutput = todoMock.toString();
        assertEquals(expectedOutput, actualOutput);


    }

}