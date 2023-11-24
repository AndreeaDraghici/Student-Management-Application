package com.ace.ucv.application;

import com.ace.ucv.application.gui.GUIApplication;
import com.ace.ucv.application.gui.iface.IApplication;

/**
 * Created by Andreea Draghici on 11/7/2023
 * Name of project: StudentManagement
 */

/*
 * A factory class responsible for creating instances of the IApplication interface based on provided criteria.
 */
public class ApplicationFactory {

    /**
     * Creates an instance of the IApplication interface based on the given command-line arguments.
     *
     * @param args The command-line arguments provided to the application.
     * @return An instance of the IApplication interface.
     * @throws RuntimeException if the provided arguments are not supported.
     */
    public IApplication applicationRunner(String[] args) {
        if (args.length == 0) {
            return new GUIApplication();
        } else {
            throw new RuntimeException("Tool is just running using the graphical user interface functionality!");
        }
    }

}
