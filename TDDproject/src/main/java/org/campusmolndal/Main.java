package org.campusmolndal;

public class Main {
    public static void main(String[] args) {

        System.out.printf("Hello and welcome!");

        MongoDBFacade db = new MongoDBFacade();

        //Skapar att- göra objekter
        Todo todoList = new Todo("Att göra idag", "ej klart", "");
        db.insertOne(todoList);




    }
}

