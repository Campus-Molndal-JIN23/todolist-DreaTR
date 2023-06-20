package org.campusmolndal;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TodoTest {

    private Todo todoMock;
    private Document docMock;

    @BeforeEach
    void setUp() {

        todoMock = mock(Todo.class);
        docMock = mock(Document.class);


        Todo mockedTodo = new Todo("mocked text", "mocked done", "mocked id");
        Todo todo = new Todo("text", "done", "id");


        when(todoMock.getText()).thenReturn(mockedTodo.getText());
        when (todoMock.getDone()).thenReturn(mockedTodo.getDone());
        when(todoMock.get_id()).thenReturn(mockedTodo.get_id());
        when(todoMock.toString()).thenReturn("text, done, id");
        when(docMock.getString("text")).thenReturn("text");
        when(docMock.getString("done")).thenReturn("done");
        when(docMock.getString("id")).thenReturn("id");


    }

    @Test
    void getText() {

        String mockedText = "mocked text";

        String actualResult = todoMock.getText();

        assertEquals(mockedText, actualResult);

    }

    @Test
    void getDone() {

        String mockedDone = "mocked done";

        String actualResult = todoMock.getDone();

        assertEquals(mockedDone, actualResult);

    }

    @Test
    void get_id() {

        String mockedId = "mocked id";

        String actualResult = todoMock.get_id();

        assertEquals(mockedId, actualResult);

    }

    @Test
    void testToString() {

        String expectedText = "text";
        String expectedDone = "done";
        String expectedId = "id";

        String actualResult = todoMock.toString();

        assertEquals(expectedText + ", " + expectedDone + ", " + expectedId, actualResult);

    }

    @Test
    void fromDoc() {

        Todo result = Todo.fromDoc(docMock);

        assertEquals("text", result.getText());
        assertEquals("done", result.getDone());
        assertEquals("id", result.get_id());
    }

    @Test
    void toDoc() {

        Todo todo = new Todo("text", "done", "id");

        Document result = todo.toDoc();

        assertEquals("text", result.getString("text"));
        assertEquals("done", result.getString("done"));
        assertEquals("id", result.getString("_id"));


    }
}