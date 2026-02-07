import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibrarySystem {
    private static List<Patron> patrons = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add New Patron (Manual)");
            System.out.println("2. Load Patrons from File");
            System.out.println("3. Remove Patron by ID");
            System.out.println("4. Display All Patrons");
            System.out.println("5. Save Current Data to File");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addPatronManual();
                case 2 -> loadFromFile();
                case 3 -> removePatron();
                case 4 -> displayPatrons();
                case 5 -> saveToFile();
            }
        } while (choice != 6);
    }

    private static void loadFromFile() {
        System.out.print("Enter filename (e.g., patrons.txt): ");
        String filename = scanner.nextLine();
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split("-");
                if (data.length == 4) {
                    patrons.add(new Patron(data[0].trim(), data[1].trim(), data[2].trim(), Double.parseDouble(data[3].trim())));
                }
            }
            System.out.println("Data loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    private static void saveToFile() {
        System.out.print("Enter filename to save as: ");
        String filename = scanner.nextLine();
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Patron p : patrons) {
                writer.println(p.getId() + "-" + p.getName() + "-" + p.getAddress() + "-" + p.getFine());
            }
            System.out.println("Records saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
    // menu setup
    private static void addPatronManual() {
        String id;
        // Validate 7-digit ID
        while (true) {
            System.out.print("Enter 7-digit ID: ");
            id = scanner.nextLine();
            if (id.matches("^\\d{7}$")) {
                break;
            } else {
                System.out.println("Invalid! Must be exactly 7 digits.");
            }
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        double fine = -1;
        // Validate Fine Range (0 to 250)
        while (fine < 0 || fine > 250) {
            System.out.print("Enter Fine (0.00 - 250.00): ");
            if (scanner.hasNextDouble()) {
                fine = scanner.nextDouble();
                if (fine < 0 || fine > 250) {
                    System.out.println("Invalid range! Please enter a value between 0 and 250.");
                }
            } else {
                System.out.println("Invalid input! Please enter a numerical value.");
                scanner.next();
            }
        }
        scanner.nextLine();

        patrons.add(new Patron(id, name, address, fine));
        System.out.println("Patron added successfully.");
    }

    private static void removePatron() {
        System.out.print("Enter ID to remove: ");
        String id = scanner.nextLine();
        patrons.removeIf(p -> p.getId().equals(id));
        System.out.println("Removal process complete.");
    }

    private static void displayPatrons() {
        if (patrons.isEmpty()) System.out.println("The list is empty.");
        else patrons.forEach(System.out::println);
    }
} 

 

 