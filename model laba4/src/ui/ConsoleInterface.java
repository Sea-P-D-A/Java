package ui;

import service.ProfileService;

import java.util.Scanner;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConsoleInterface {
    public static final Integer MAX_COUNT_ERROR = 3;
    public static final Logger LOGGER = Logger.getLogger(ConsoleInterface.class.getName());
    private final ProfileService profileService;

    public ConsoleInterface(ProfileService profileService) {
        this.profileService = profileService;
    }

    public void start() {
        printMenu();
        processWork();
    }

    private void processWork() {
        int errorCount = 0;

        try (Scanner scanner = new Scanner(System.in)) {
            while (errorCount < MAX_COUNT_ERROR) {
                System.out.println("Enter command: ");
                try {
                    allCommand(scanner);
                } catch (IllegalArgumentException exception) {
                    LOGGER.log(Level.WARNING, "Invalid command", exception);
                    errorCount++;
                }
            }
        }
    }

    private void allCommand(Scanner scanner) {
        Command nextCommand = Command.valueOf(scanner.nextLine().toUpperCase());

        switch (nextCommand) {
            case LOADFILE -> {
                System.out.println("Enter path to file:");
                String fileName = scanner.nextLine();
                String fullPath = "src/Data/" + fileName + ".txt";
                profileService.loadFile(fullPath);
            }
            case SEARCH -> {
                System.out.println("Enter full name:");
                String fio = scanner.nextLine();
                profileService.searchProfileByFio(fio);            }
            case ADDUSER -> {
                System.out.println("Enter name");
                String name = scanner.nextLine();
                System.out.println("Enter age");
                int age = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter phone number");
                String phoneNumber = scanner.nextLine();
                System.out.println("Enter sex");
                String sex = scanner.nextLine();
                System.out.println("Enter address");
                String address = scanner.nextLine();
                profileService.addUser(name, age, phoneNumber, sex, address);
            }
            case REMOVEUSER -> {
                System.out.println("Enter name to remove");
                String nameToRemove = scanner.nextLine();
                profileService.removeUser(nameToRemove);
            }
            case SAVEFILE -> profileService.saveFile();
            case SAVEFILEAS -> {
                System.out.println("Enter new file name:");
                String newFileName = scanner.nextLine();
                profileService.saveFile(newFileName);
            }
            case EXIT -> {
                System.exit(0);
            }
        }
    }

    private void printMenu() {
        System.out.println("\n\nYou can use next commands:");
        System.out.println(Arrays.toString(Command.values()));
    }


}
