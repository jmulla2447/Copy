package com.kamlesh.bhavcopy.controller;

import com.kamlesh.bhavcopy.service.CopyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class CopyCommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(CopyCommandLineRunner.class);

    private final CopyService copyService;

    public CopyCommandLineRunner(CopyService copyService) {
        this.copyService = copyService;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Application started. Awaiting user commands...");

        try (Scanner scanner = new Scanner(System.in)) {
            String command;

            while (true) {
                System.out.print("> ");
                command = scanner.nextLine().trim();

                if (command.equalsIgnoreCase("quit")) {
                    logger.info("User requested exit. Exiting...");
                    System.out.println("Exiting...");
                    break;
                }

                handleCommand(command);
            }
        } catch (Exception ex) {
            logger.error("An unexpected error occurred: {}", ex.getMessage());
        }
    }

    private void handleCommand(String command) {
        String[] parts = command.split("\\s+");

        try {
            if (parts.length == 1 && parts[0].equalsIgnoreCase("load")) {
                handleLoadCommand();
            } else if (parts.length >= 3 && parts[0].equalsIgnoreCase("query")) {
                handleQueryCommand(parts);
            } else {
                logger.warn("Invalid command format: {}", command);
                System.out.println("Invalid command. Try 'load' or 'query <type> <params>'.");
            }
        } catch (Exception ex) {
            logger.error("Error handling command: {}", command, ex);
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void handleLoadCommand() {
        logger.info("Executing 'load' command");
        try {
            copyService.loadCopy();
            logger.info("Data loaded successfully");
            System.out.println("Data loaded successfully.");
        } catch (Exception e) {
            logger.error("Failed to load data: {}", e.getMessage());
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    private void handleQueryCommand(String[] parts) {
        String queryType = parts[1].toUpperCase();
        String[] params = new String[parts.length - 2];
        System.arraycopy(parts, 2, params, 0, parts.length - 2);

        logger.info("Executing query of type '{}' with parameters {}", queryType, params);

        try {
            Object result = copyService.handleQuery(queryType, params);
            printResult(result);
        } catch (Exception ex) {
            logger.error("Error executing query '{}' with params {}: {}", queryType, params, ex.getMessage());
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void printResult(Object result) {
        if (result instanceof List<?>) {
            List<?> list = (List<?>) result;
            list.forEach(System.out::println);
        } else {
            System.out.println(result);
        }
    }
}
