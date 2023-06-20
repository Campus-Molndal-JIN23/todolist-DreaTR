package org.campusmolndal;

public class Main {
    public static void main(String[] args) {

        System.out.print("Hello and welcome!");

        MongoDBFacade db = new MongoDBFacade();

        //Skapar to-do objekter
        Todo todoList = new Todo("Möte kl 9:00", "ja", "");
        db.insertOne(todoList);

        Todo todoList2 = new Todo("Träning med Marcus kl 11:30", "ja", "");
        db.insertOne(todoList2);

        Todo todoList3 = new Todo("Läkarbesök kl 13:00", "nej", "");
        db.insertOne(todoList3);

        Todo todoList4 = new Todo("Möte kl 14", "nej", "");
        db.insertOne(todoList4);


    }
}

