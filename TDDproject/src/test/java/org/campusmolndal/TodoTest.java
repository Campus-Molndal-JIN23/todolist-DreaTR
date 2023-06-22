package org.campusmolndal;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

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

        // Ställ in testdata
        String newText = "Uppdaterad text";

        // Kalla metoden som testas
        todo.setText(newText);

        // Bekräfta resultatet
        assertEquals(newText, todo.getText());
    }
    @Test
    void setDone_updatesDoneStatusInTodo() {

        boolean newDoneStatus = true;

        todo.setDone(newDoneStatus);

        assertEquals(newDoneStatus, todo.isDone());
    }
    @Test
    void isDone_returnsFalseForNewTodo() {

        boolean doneStatus = todo.isDone();

        assertFalse(doneStatus);
    }
    @Test
    void fromDoc_returnsNewTodoInstanceWithNullData() {

        Document doc = null;

        Todo result = Todo.fromDoc(doc);

        assertEquals("", result.getText());
        assertEquals("", result.getDone());
        assertEquals("", result.get_id());
    }
    @Test
    void toDoc_returnsDocumentWithTodoData() {

        todo.setText("Sample text");
        todo.setDone(true);
        todo.set_id("123");

        Document doc = todo.toDoc();

        assertNotNull(doc);
        assertEquals("Sample text", doc.getString("text"));
        assertEquals("true", doc.getString("done"));
        assertEquals("123", doc.getString("_id"));
    }


}