package org.campusmolndal;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Menu {

    private final MongoDB db;
    private final Scanner scanner;

    public Menu() {
        db = new MongoDB("mongodb://localhost:27017", "todoDB", "todos");
        scanner = new Scanner(System.in);
    }
    public void start() {
        System.out.println("=============================");
        System.out.println("| Välkommen till Todo Appen |");
        System.out.println("| Gör ett följande val:     |");
        System.out.println("=============================");

        while (true) {
            System.out.println("1. Lägg till en uppgift");
            System.out.println("2. Visa en uppgift");
            System.out.println("3. Visa alla uppgifter");
            System.out.println("4. Uppdatera en uppgift");
            System.out.println("5. Radera en uppgift");
            System.out.println("6. Radera alla uppgifter");
            System.out.println("0. Avsluta programmet");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    addTodo();
                    break;
                case 2:
                    readTodo();
                    break;
                case 3:
                    readAllTodos();
                    break;
                case 4:
                    updateTodo();
                    break;
                case 5:
                    deleteTodo();
                    break;
                case 6:
                    db.deleteAllTodos();
                    System.out.println("Alla uppgifter har raderats.");
                    break;
                case 0:
                    db.closeTodo();
                    return;
                default:
                    System.out.println("Ogiltigt val. Var god försök igen.");
            }

        }
    }

    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Felaktig input. Ange ett giltigt heltal.");
            }
        }
    }

    private boolean getBooleanInput() {
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("true")) {
                return true;
            } else if (input.equalsIgnoreCase("false")) {
                return false;
            }
            System.out.println("Felaktig input. Vänligen ange \"true\" eller \"false\".'.");
        }
    }

    // Skapar en todo
    private void addTodo() {
        System.out.println("Ange ny uppgift:");
        String text = scanner.nextLine();
        System.out.println("Är uppgiften gjord? |true|false|:");
        boolean done = getBooleanInput();

        Todo todo = new Todo();
        todo.setText(text);
        todo.setDone(done);

        db.createTodo(todo);
        System.out.println("Uppgift har lagts till.");
    }

    // Visar en todo
    private void readTodo() {
        System.out.println("Ange id för uppgiften att läsa:");
        String id = scanner.nextLine();

        Optional<Todo> todo = db.read(id);

        if (todo.isPresent()) {
            System.out.println("Uppgift hittade:");
            System.out.println(todo.get());
        } else {
            System.out.println("Ingen uppgift hittades med det angivna ID:t.");
        }
    }

    // Visar alla todo:s
    private void readAllTodos() {
        List<Todo> todos = db.readAllTodo();
        if (!todos.isEmpty()) {
            System.out.println("Alla todos:");
            int count = 1;
            for (Todo todo : todos) {
                System.out.printf("%d. %s%n", count++, todo);
            }
        } else {
            System.out.println("Inga uppgifter hittades.");
        }
    }

    // Uppdaterar en todo
    private void updateTodo() {
        System.out.println("Ange id för uppgiften för att uppdatera:");
        String id = scanner.nextLine();

        Optional<Todo> todo = db.read(id);

        if (todo.isPresent()) {
            System.out.println("Ange den nya uppgiften:");
            String text = scanner.nextLine();
            System.out.println("Är uppgiften gjord? |true|false|:");
            boolean done = getBooleanInput();

            todo.get().setText(text);
            todo.get().setDone(done);
            db.updateTodo(todo.get());

            System.out.println("Todo har uppdaterats.");
        } else {
            System.out.println("Ingen uppgift hittades med det angivna ID:t.");
        }
    }

    // Raderar todo
    private void deleteTodo() {
        System.out.println("Ange id för att radera uppgiften:");
        String id = scanner.nextLine();

        Optional<Todo> todo = db.read(id);

        if (todo.isPresent()) {
            db.deleteTodo(id);
            System.out.println("Todo har raderats bort.");
        } else {
            System.out.println("Ingen uppgift hittades med det angivna ID:t.");
        }
    }

    // Stänger menyn
    public void close() {
        System.out.println("\nDu loggas ut");
        scanner.close();
    }

    // Main
    public static void main(String[] args) {

        // Inaktiverar MongoDB driver logging
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        Menu menu = new Menu ();
        menu.start();
        menu.close();
    }

} //Slut på class


