package com.ace.ucv.application.cli;

import com.ace.ucv.application.cli.builder.CLIBuilder;
import com.ace.ucv.application.gui.iface.IApplication;
import com.ace.ucv.model.cli.Argument;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Andreea Draghici on 12/2/2023
 * Name of project: StudentManagement
 */
public class CLIApplication implements IApplication {

    private static final Logger logger = LogManager.getLogger(CLIApplication.class);

    private static final String USAGE_MSG = " [-options] [args ...] ";
    private static final CommandLineParser commandLineParser = new DefaultParser();
    private static final Options options = new Options();
    private static final Options helpOptions = new Options();

    static {
        helpOptions.addOption(Option.builder("h").longOpt("help").hasArg(false).desc("Displays this help section.").build());
        options.addOption(Option.builder("s").required(true).hasArg(true).desc("Represents the student file path.").build());
        options.addOption(Option.builder("d").required(true).hasArg(true).desc("Represents the discipline file path.").build());
        options.addOption(Option.builder("g").required(true).hasArg(true).desc("Represents the grade file path.").build());
        options.addOption(Option.builder("o").required(true).hasArg(true).desc("Represents the directory in which the output file will be generated.").build());
    }

    /**
     * Function to handle the arguments passed via CLI
     *
     * @param args - arguments
     */

    @Override
    public void run(String[] args) {
        CommandLine commandLine;
        try {
            commandLine = commandLineParser.parse(helpOptions, args, true);

            if (commandLine.getOptions().length == 0) {
                Argument argument = new Argument();
                commandLine = commandLineParser.parse(options, args);
                getArgumentOptions(argument, commandLine);
                CLIBuilder builder = new CLIBuilder();
                builder.executeParsing(argument);
            } else {
                printHelp();
            }
        } catch (ParseException e) {
            logger.warn("Failed to execute parsing due to: " + e.getMessage());
        }

    }

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
     * Prints the help section to the console
     */

    private void printHelp() {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp(USAGE_MSG, "\n-----AVAILABLE OPTIONS-----\n", options, "");
    }

}
