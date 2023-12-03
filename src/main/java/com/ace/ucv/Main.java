package com.ace.ucv;

import com.ace.ucv.application.ApplicationFactory;
import com.ace.ucv.application.IApplication;


/**
 * The entry point for the GUI application.
 */
public class Main {

    /**
     * The main method responsible for initiating and running the GUI application.
     *
     * @param args Command-line arguments provided to the application.
     */
    public static void main(String[] args) {

        // Create an instance of the ApplicationFactory
        ApplicationFactory factory = new ApplicationFactory();

        // Use the factory to obtain an instance of IApplication based on command-line arguments
        IApplication application = factory.applicationRunner(args);

        // Run the obtained application
        application.run(args);

    }
}