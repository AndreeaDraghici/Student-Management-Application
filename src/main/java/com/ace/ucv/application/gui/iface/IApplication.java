package com.ace.ucv.application.gui.iface;

/**
 * Created by Andreea Draghici on 11/7/2023
 * Name of project: StudentManagement
 */

/*
 * This interface defines the contract that all applications in the system must adhere to.
 * Any class implementing this interface should provide an implementation for the 'run' method,
 * serving as the entry point for the application's execution.
 */
public interface IApplication {

    /**
     * The main method responsible for executing the application logic.
     *
     * @param args The command-line arguments passed to the application.
     * <p>
     *             These parameters can be used to customize the behavior of the application.
     *             For example, specifying input files or configuration settings.
     *             If no arguments are provided, the application may use default settings.
     */
    void run(String[] args);
}
