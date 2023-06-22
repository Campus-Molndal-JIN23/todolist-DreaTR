package org.campusmolndal;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodoTest {

    @Mock
    private MongoDBFacade mongoDBFacadeMock;
    private Todo todo;

    private Document doc;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        todo = new Todo(mongoDBFacadeMock);
    }

    @Test
    void setText_updatesTextInTodo() {

        // Set up test data
        String newText = "Uppdaterad text";

        // Call the method under test
        todo.setText(newText);

        // Assert the result
        assertEquals(newText, todo.getText());
    }
    @Test
    void setDone_updatesDoneStatusInTodo() {
        // Set up test data
        boolean newDoneStatus = true;

        // Call the method under test
        todo.setDone(newDoneStatus);

        // Assert the result
        assertEquals(newDoneStatus, todo.isDone());
    }
    @Test
    void isDone_returnsFalseForNewTodo() {
        // Call the method under test
        boolean doneStatus = todo.isDone();

        // Assert the result
        assertFalse(doneStatus);
    }
    @Test
    void fromDoc_returnsNewTodoInstanceWithNullData() {
        // Set up test data
        Document doc = null;

        // Call the method under test
        Todo result = Todo.fromDoc(doc);

        // Assert the result
        assertEquals("", result.getText());
        assertEquals("", result.getDone());
        assertEquals("", result.get_id());
    }
    @Test
    void toDoc_returnsDocumentWithTodoData() {
        // Set up test data
        todo.setText("Sample text");
        todo.setDone(true);
        todo.set_id("123");

        // Call the method under test
        Document doc = todo.toDoc();

        // Assert the result
        assertNotNull(doc);
        assertEquals("Sample text", doc.getString("text"));
        assertEquals("true", doc.getString("done"));
        assertEquals("123", doc.getString("_id"));
    }


}