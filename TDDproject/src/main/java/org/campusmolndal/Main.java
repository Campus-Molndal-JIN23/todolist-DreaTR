package org.campusmolndal;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {

        // Inaktiverar MongoDB driver logging
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        System.out.println("Hello and welcome!");

        MongoDBFacade db = new MongoDBFacade();

        //Skapar to-do objekter i MongoDB
        Todo todoList = new Todo("Möte kl 9:00", "ja", "");
        db.insertOne(todoList);

        Todo todoList2 = new Todo("Träning med Marcus kl 11:30", "ja", "");
        db.insertOne(todoList2);

        Todo todoList3 = new Todo("Läkarbesök kl 13:00", "nej", "");
        db.insertOne(todoList3);

        Todo todoList4 = new Todo("Möte kl 14", "nej", "");
        db.insertOne(todoList4);

        Todo todoList5 = new Todo("Lämna bilen på verkstad kl 16:30", "nej", "");
        db.insertOne(todoList5);

    }
}

