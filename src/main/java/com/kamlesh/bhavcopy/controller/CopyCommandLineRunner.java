package com.kamlesh.bhavcopy.controller;

import com.kamlesh.bhavcopy.service.CopyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class CopyCommandLineRunner implements CommandLineRunner {

    private final CopyService copyService;

    public CopyCommandLineRunner(CopyService copyService){
        this.copyService = copyService;
    }
    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.print("> ");
            command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("quit")) {
                System.out.println("Exiting...");
                break;
            }

            String[] parts = command.split("\\s+");
            try {
                if (parts.length == 2 && parts[0].equalsIgnoreCase("load")) {
                    String filename = parts[1];
                    copyService.loadCopy(filename);
                } else if (parts.length == 3 && parts[0].equalsIgnoreCase("query")) {
                    String queryType = parts[1].toUpperCase();
                    String[] params = new String[parts.length - 2];
                    System.arraycopy(parts, 2, params, 0, parts.length - 2);

                    // Handle different query types dynamically
                    Object result = copyService.handleQuery(queryType, params);

                    // Print the result (handling both List and String responses)
                    if (result instanceof List<?>) {
                        List<?> list = (List<?>) result;
                        for (Object obj : list) {
                            System.out.println(obj);
                        }
                    } else {
                        System.out.println(result);
                    }
                } else {
                    System.out.println("Invalid command. Try 'load <filename>' or 'query <type> <params>'.");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }

        scanner.close();
    }
}
