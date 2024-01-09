package org.example;

import java.util.Scanner;

public class AddressBookMain {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        AddressBookSystem addressBookSystem = new AddressBookSystem();

        while (true) {
            System.out.println("\nAddress Book System Menu:");
            System.out.println("1. Add new Address Book");
            System.out.println("2. Select Address Book");
            System.out.println("3. Display Address Books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the name of the new Address Book: ");
                    String newAddressBookName = scanner.nextLine();
                    addressBookSystem.addAddressBook(newAddressBookName);
                    break;

                case 2:
                    System.out.print("Enter the name of the Address Book to select: ");
                    String selectedAddressBookName = scanner.nextLine();
                    AddressBook selectedAddressBook = addressBookSystem.getAddressBook(selectedAddressBookName);

                    if (selectedAddressBook != null) {
                        selectedAddressBook.mainMenu();
                    } else {
                        System.out.println("Address Book not found.");
                    }
                    break;

                case 3:
                    addressBookSystem.displayAddressBooks();
                    break;

                case 4:
                    System.out.println("Exiting Address Book System. Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}