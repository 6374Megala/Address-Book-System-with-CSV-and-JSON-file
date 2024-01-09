package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AddressBookSystem {
    private Map<String, AddressBook> addressBooks = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void saveAddressBookToFile(String addressBookName, String fileName) {
        AddressBook addressBook = addressBooks.get(addressBookName);
        if (addressBook != null) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(addressBook.getContacts());
                System.out.println("Address Book saved to file '" + fileName + "' successfully.");
            } catch (IOException e) {
                System.out.println("Error saving Address Book to file: " + e.getMessage());
            }
        } else {
            System.out.println("Address Book '" + addressBookName + "' not found.");
        }
    }

    public void loadAddressBookFromFile(String addressBookName, String fileName) {
        AddressBook addressBook = addressBooks.get(addressBookName);
        if (addressBook != null) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
                List<Contact> loadedContacts = (List<Contact>) ois.readObject();
                addressBook.getContacts().addAll(loadedContacts);
                System.out.println("Address Book loaded from file '" + fileName + "' successfully.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading Address Book from file: " + e.getMessage());
            }
        } else {
            System.out.println("Address Book '" + addressBookName + "' not found.");
        }
    }

    public void addAddressBook(String addressBookName) {
        if (addressBooks.containsKey(addressBookName)) {
            System.out.println("Address Book with the same name already exists.");
        } else {
            AddressBook newAddressBook = new AddressBook();
            addressBooks.put(addressBookName, newAddressBook);
            System.out.println("Address Book '" + addressBookName + "' added successfully.");
        }
    }

    public AddressBook getAddressBook(String addressBookName) {
        return addressBooks.get(addressBookName);
    }

    public void displayAddressBooks() {
        System.out.println("Address Books in the System:");
        for (String addressBookName : addressBooks.keySet()) {
            System.out.println("- " + addressBookName);
        }
    }
    public void searchPersonByCityOrState(String cityOrState) {
        System.out.println("\nSearch Result for '" + cityOrState + "':");


        addressBooks.values().stream()
                .flatMap(addressBook -> addressBook.getContacts().stream())
                .filter(contact -> cityOrState.equalsIgnoreCase(contact.getCity()) || cityOrState.equalsIgnoreCase(contact.getState()))
                .forEach(contact -> System.out.println("Name: " + contact.getFullName() +
                        "\nAddress: " + contact.getAddress() +
                        "\nCity: " + contact.getCity() +
                        "\nState: " + contact.getState() +
                        "\nZIP: " + contact.getZip() +
                        "\nPhone Number: " + contact.getPhoneNumber() +
                        "\nEmail: " + contact.getEmail() +
                        "\n------------"));
    }

    public void displaySortedContacts(String addressBookName) {
        AddressBook addressBook = addressBooks.get(addressBookName);
        if (addressBook != null) {
            addressBook.sortContactsByName();
            addressBook.displayContacts();
        } else {
            System.out.println("Address Book '" + addressBookName + "' not found.");
        }
    }

    public void displaySortedContactsByCity(String addressBookName) {
        AddressBook addressBook = addressBooks.get(addressBookName);
        if (addressBook != null) {
            addressBook.sortContactsByCity();
            addressBook.displayContacts();
        } else {
            System.out.println("Address Book '" + addressBookName + "' not found.");
        }
    }

    public void displaySortedContactsByState(String addressBookName) {
        AddressBook addressBook = addressBooks.get(addressBookName);
        if (addressBook != null) {
            addressBook.sortContactsByState();
            addressBook.displayContacts();
        } else {
            System.out.println("Address Book '" + addressBookName + "' not found.");
        }
    }

    public void displaySortedContactsByZip(String addressBookName) {
        AddressBook addressBook = addressBooks.get(addressBookName);
        if (addressBook != null) {
            addressBook.sortContactsByZip();
            addressBook.displayContacts();
        } else {
            System.out.println("Address Book '" + addressBookName + "' not found.");
        }
    }


    private void displayPersonDetails(Contact contact) {
        System.out.println("Name: " + contact.getFullName() +
                "\nAddress: " + contact.getAddress() +
                "\nCity: " + contact.getCity() +
                "\nState: " + contact.getState() +
                "\nZIP: " + contact.getZip() +
                "\nPhone Number: " + contact.getPhoneNumber() +
                "\nEmail: " + contact.getEmail() +
                "\n------------");
    }
}
