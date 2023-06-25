package org.campusmolndal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TodoApp {

    private List<String> todos;
    private Scanner scanner;

    public TodoApp() {
        todos = new ArrayList<>();
        scanner = new Scanner(System.in);
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
        System.out.println("5. Avsluta");

    }
    void addTodo() {
        System.out.println("Ange en TODO:");
        String todo = scanner.nextLine();
        todos.add(todo);
        System.out.println("TODO tillagd.");
    }
    private void removeTodo() {
        System.out.println("Ange indexet för den TODO som ska tas bort:");
        int index = readIntInput();

        if (index >= 0 && index < todos.size()) {
            String removedTodo = todos.remove(index);
            System.out.println("TODO '" + removedTodo + " är borttagen.");
        } else {
            System.out.println("Ogiltigt index.");
        }
    }
    private void updateTodo() {
        System.out.println("Ange indexet för den TODO som ska uppdateras:");
        int index = readIntInput();

        if (index >= 0 && index < todos.size()) {
            System.out.println("Ange den nya TODO:");
            String newTodo = scanner.nextLine();
            todos.set(index, newTodo);
            System.out.println("TODO uppdaterad.");
        } else {
            System.out.println("Ogiltigt index.");
        }
    }
    private void listTodos() {
        if (todos.isEmpty()) {
            System.out.println("Inga TODOs att visa.");
        } else {
            System.out.println("TODOs:");
            for (int i = 0; i < todos.size(); i++) {
                System.out.println(i + ". " + todos.get(i));
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
    public static void main(String[] args) {
        TodoApp todoApp = new TodoApp();
        todoApp.run();
    }
}



