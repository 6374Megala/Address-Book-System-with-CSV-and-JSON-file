package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class AddressBookJSON {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void writeAddressBookToJSON(String fileName, AddressBook addressBook) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(addressBook, writer);
            System.out.println("Address Book written to JSON file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AddressBook readAddressBookFromJSON(String fileName) {
        AddressBook addressBook = null;
        try (Reader reader = new FileReader(fileName)) {
            addressBook = gson.fromJson(reader, AddressBook.class);
            System.out.println("Address Book read from JSON file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addressBook;
    }

    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();

        writeAddressBookToJSON("address_book.json", addressBook);

        AddressBook readAddressBook = readAddressBookFromJSON("address_book.json");

        if (readAddressBook != null) {
            readAddressBook.displayContacts();
        }
    }

}
