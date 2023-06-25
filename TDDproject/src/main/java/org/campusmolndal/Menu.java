package org.campusmolndal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private MongoDBFacade db;

    private List<Todo> todos;
    private Scanner scanner;

    public Menu() {
        todos = new ArrayList<>();
        scanner = new Scanner(System.in);
        db = new MongoDBFacade();
    }
    public void run() {
        boolean running = true;

        while (running) {
            printMenu();
                int choice = readIntInput();

            switch (choice) {
                case 1:
                    addTodo();
                    break;
                case 2:
                    removeTodo();
                    break;
                case 3:
                    listTodos();
                    break;
                case 4:
                    updateTodo();
                    break;
                case 5:
                    updateDoneStatus();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Ogiltigt val. Försök igen.");
                    break;
            }
        }
    }

    private void printMenu() {
        System.out.println("Välkommen till Todo appen!");
        System.out.println("==========================");
        System.out.println("Välj en åtgärd:");
        System.out.println("1. Lägg till en TODO");
        System.out.println("2. Ta bort en TODO");
        System.out.println("3. Visa alla TODOs");
        System.out.println("4. Uppdatera en TODO");
        System.out.println("5. Uppdatera done-status för en TODO");
        System.out.println("6. Avsluta");

    }
    void addTodo() {
        System.out.println("Ange en TODO:");
        String todoText = scanner.nextLine();
        Todo newTodo = new Todo(todoText, false);
        db.insertOne(newTodo);
        todos.add(newTodo);
        System.out.println("TODO tillagd.");
    }
    private void removeTodo() {

        System.out.println("Ange indexet för den TODO som ska tas bort:");
        int index = readIntInput();

        if (index >= 0 && index < todos.size()) {
            Todo removedTodo = todos.remove(index);
            db.Delete(removedTodo.getId());
            System.out.println("TODO '" + removedTodo.getText() + "' är borttagen.");
        } else {
            System.out.println("Ogiltigt index.");
        }
    }
    private void updateTodo() {
        System.out.println("Ange indexet för den TODO som ska uppdateras:");
        int index = readIntInput();

        if (index >= 0 && index < todos.size()) {
            System.out.println("Ange den nya TODO:");
            String newTodoText = scanner.nextLine();
            Todo todoToUpdate = todos.get(index);
            todoToUpdate.setText(newTodoText);
            db.updateOne(todoToUpdate);
            System.out.println("TODO uppdaterad.");
        } else {
            System.out.println("Ogiltigt index.");
        }
    }
    private void updateDoneStatus() {
        System.out.println("Ange indexet för den TODO vars done-status ska uppdateras:");
        int index = readIntInput();

        if (index >= 0 && index < todos.size()) {
            System.out.println("Ange den nya done-statusen (true/false):");
            boolean newDoneStatus = Boolean.parseBoolean(scanner.nextLine());
            Todo todoToUpdate = todos.get(index);
            todoToUpdate.setDone(newDoneStatus);
            db.updateOne(todoToUpdate);
            System.out.println("Done");
        }
    }

    private void listTodos() {
        ArrayList<Todo> todos = db.getAllTodos();
        if (todos.isEmpty()) {
            System.out.println("Inga TODOs att visa.");
        } else {
            System.out.println("TODOs:");
            for (int i = 0; i < todos.size(); i++) {
                Todo todo = todos.get(i);
                System.out.println(i + ". " + todo.getText() + " (Done: " + todo.isDone() + ")");
            }
        }
    }
    private int readIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ogiltigt värde. Försök igen.");
            }
        }
    }

}



