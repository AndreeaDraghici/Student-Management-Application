package com.ace.ucv.application.cli;

import com.ace.ucv.application.cli.builder.CLIBuilder;
import com.ace.ucv.application.IApplication;
import com.ace.ucv.model.cli.Argument;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Andreea Draghici on 12/2/2023
 * Name of project: StudentManagement
 */

/*
 * CLIApplication class represents the Command Line Interface for the application.
 * Implements the IApplication interface.
 */
public class CLIApplication implements IApplication {

    private static final Logger logger = LogManager.getLogger(CLIApplication.class);

    // Usage message for the command line
    private static final String USAGE_MSG = " [-options] [args ...] ";

    // CommandLineParser and Options objects for handling command line arguments
    private static final CommandLineParser commandLineParser = new DefaultParser();
    private static final Options options = new Options();
    private static final Options helpOptions = new Options();

    // Static block to initialize help options and command line options
    static {
        // Help option for displaying help section
        helpOptions.addOption(Option.builder("h").longOpt("help").hasArg(false).desc("Displays this help section.").build());

        // Command line options for specifying file paths and output directory
        options.addOption(Option.builder("s").required(true).hasArg(true).desc("Represents the student file path.").build());
        options.addOption(Option.builder("d").required(true).hasArg(true).desc("Represents the discipline file path.").build());
        options.addOption(Option.builder("g").required(true).hasArg(true).desc("Represents the grade file path.").build());
        options.addOption(Option.builder("o").required(true).hasArg(true).desc("Represents the directory in which the output file will be generated.").build());
    }

    /**
     * Function to handle the arguments passed via CLI.
     *
     * @param args - Command line arguments
     */
    @Override
    public void run(String[] args) {
        CommandLine commandLine;
        try {
            commandLine = commandLineParser.parse(helpOptions, args, true);

            // If no help options are present, parse the main options
            if (commandLine.getOptions().length == 0) {
                Argument argument = new Argument();
                commandLine = commandLineParser.parse(options, args);
                getArgumentOptions(argument, commandLine);
                CLIBuilder builder = new CLIBuilder();
                builder.executeParsing(argument);
            } else {
                // If help options are present, print help
                printHelp();
            }
        } catch (ParseException e) {
            logger.warn("Failed to execute parsing due to: " + e.getMessage());
        }

    }

    /**
     * Extracts and sets the argument options from the CommandLine object.
     *
     * @param argument    - Argument object to store the options
     * @param commandLine - CommandLine object containing parsed options
     */
    private void getArgumentOptions(Argument argument, CommandLine commandLine) {
        argument.setStudentFilePath(commandLine.hasOption("s") ? commandLine.getOptionValue("s") : "");
        logger.info("Student file path : " + argument.getStudentFilePath());

        argument.setDisciplineFilePath(commandLine.hasOption("d") ? commandLine.getOptionValue("d") : "");
        logger.info("Discipline file path : " + argument.getDisciplineFilePath());

        argument.setGradeFilePath(commandLine.hasOption("g") ? commandLine.getOptionValue("g") : "");
        logger.info("Grade file path : " + argument.getGradeFilePath());

        argument.setOutputCatalogPath(commandLine.hasOption("o") ? commandLine.getOptionValue("o") : "");
        logger.info("Output catalog path : " + argument.getOutputCatalogPath());
    }

    /**
     * Prints the help section to the console.
     */
    private void printHelp() {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp(USAGE_MSG, "\n-----AVAILABLE OPTIONS-----\n", options, "");
    }

}
