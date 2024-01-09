package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AddressBookCSV {
    public static void writeAddressBookToCSV(String fileName, AddressBook addressBook) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
            for (Contact contact : addressBook.getContacts()) {
                String[] record = {
                        contact.getFirstName(),
                        contact.getLastName(),
                        contact.getAddress(),
                        contact.getCity(),
                        contact.getState(),
                        contact.getZip(),
                        contact.getPhoneNumber(),
                        contact.getEmail()
                };
                writer.writeNext(record);
            }
            System.out.println("Address Book written to CSV file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AddressBook readAddressBookFromCSV(String fileName) {
        AddressBook addressBook = new AddressBook();
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            String[] record=new String[8];
            while ((record = reader.readNext()) != null) {
                String firstName = record[0];
                String lastName=record[1];
                String address = record[2];
                String city = record[3];
                String state = record[4];
                String zip = record[5];
                String phoneNumber = record[6];
                String email = record[7];

                Contact contact = new Contact(firstName,lastName, address, city, state, zip, phoneNumber, email);
                addressBook.addContact(contact);
            }
            System.out.println("Address Book read from CSV file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addressBook;
    }

    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        addressBook.addContact(new Contact("John", "Doe", "123 Main St", "City1", "State1", "12345", "123-456-7890", "john@example.com"));
        addressBook.addContact(new Contact("Alice", "Smith", "456 Oak St", "City2", "State2", "67890", "987-654-3210", "alice@example.com"));

        // Write the address book to a CSV file
        writeAddressBookToCSV("address_book.csv", addressBook);


        AddressBook readAddressBook = readAddressBookFromCSV("address_book.csv");

        // Display the contacts from the read address book
        if (readAddressBook != null) {
            readAddressBook.displayContacts();
        }
    }
}
