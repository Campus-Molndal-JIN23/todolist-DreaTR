package org.campusmolndal;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {


        System.out.println("Hello and welcome");


        // Inaktiverar MongoDB driver logging
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        Menu menu = new Menu ();
        menu.start();
        menu.close();


    }
}

