package org.example;

import java.util.*;

public class AddressBook {
    private ArrayList<Contact> contacts = new ArrayList<>();
    AddressBookSystem addressBookSystem=new AddressBookSystem();

    public List<Contact> getContacts() {
        return contacts;
    }
    public void sortContactsByName() {
        contacts.sort(Contact::compareTo);
    }

    public void sortContactsByCity() {
        contacts.sort(Comparator.comparing(Contact::getCity));
    }

    public void sortContactsByState() {
        contacts.sort(Comparator.comparing(Contact::getState));
    }

    public void sortContactsByZip() {
        contacts.sort(Comparator.comparing(Contact::getZip));
    }
    public void addContact(Contact contact) {

        boolean isDuplicate = contacts.stream()
                .anyMatch(existingContact -> Objects.equals(existingContact, contact));

        if (isDuplicate) {
            System.out.println("Duplicate entry. This person already exists in the Address Book.");
        } else {
            contacts.add(contact);
            System.out.println("Contact added successfully.");
        }

    }

    public void displayContacts() {
        if (contacts.isEmpty()) {
            System.out.println("contact is empty");
        } else {
            for (Contact contact : contacts) {
                System.out.println(contact);
                System.out.println("------------");
            }
        }

    }

    public void editContact(String firstName, String lastName) {
        Contact contactToEdit = getContactByName(firstName, lastName);
        if (contactToEdit != null) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Editing contact: " + contactToEdit.getFullName());
            System.out.print("Enter new Address: ");
            String address = scanner.nextLine();
            System.out.print("Enter new City: ");
            String city = scanner.nextLine();
            System.out.print("Enter new State: ");
            String state = scanner.nextLine();
            System.out.print("Enter new ZIP: ");
            String zip = scanner.nextLine();
            System.out.print("Enter new Phone Number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Enter new Email: ");
            String email = scanner.nextLine();
            contactToEdit.setAddress(address);
            contactToEdit.setCity(city);
            contactToEdit.setState(state);
            contactToEdit.setZip(zip);
            contactToEdit.setPhoneNumber(phoneNumber);
            contactToEdit.setEmail(email);

            System.out.println("Contact updated successfully.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    public Contact getContactByName(String firstName, String lastName) {
        for (Contact contact : contacts) {
            if (contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)) {
                return contact;
            }
        }
        return null;
    }

    public void deleteContact(String firstName, String lastName) {
        Contact contactToDelete = getContactByName(firstName, lastName);
        if (contactToDelete != null) {
            contacts.remove(contactToDelete);
            System.out.println("Contact deleted successfully.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nAddress Book Menu:");
            System.out.println("1. Add new contact");
            System.out.println("2. Display contacts");
            System.out.println("3. Edit a contact");
            System.out.println("4. Delete a contact");
            System.out.println("5. Go back to Address Book System");
            System.out.println("6. Save the file");
            System.out.println("7. load the file");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Adding a new contact:");
                    System.out.print("Enter First Name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter Last Name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter City: ");
                    String city = scanner.nextLine();
                    System.out.print("Enter State: ");
                    String state = scanner.nextLine();
                    System.out.print("Enter ZIP: ");
                    String zip = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    Contact newContact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
                    addContact(newContact);
                    break;

                case 2:
                    System.out.println("Displaying contacts:");
                    displayContacts();
                    break;

                case 3:
                    System.out.println("Editing a contact:");
                    System.out.print("Enter First Name of the contact to edit: ");
                    String editFirstName = scanner.nextLine();
                    System.out.print("Enter Last Name of the contact to edit: ");
                    String editLastName = scanner.nextLine();
                    editContact(editFirstName, editLastName);
                    break;

                case 4:
                    System.out.println("Deleting a contact:");
                    System.out.print("Enter First Name of the contact to delete: ");
                    String deleteFirstName = scanner.nextLine();
                    System.out.print("Enter Last Name of the contact to delete: ");
                    String deleteLastName = scanner.nextLine();
                    deleteContact(deleteFirstName, deleteLastName);
                    break;

                case 5:
                    System.out.println("Going back to Address Book System.");
                    return;

                case 6:
                    System.out.print("Enter the name of the Address Book to save: ");
                    String saveAddressBookName = scanner.nextLine();
                    System.out.print("Enter the file name to save the Address Book: ");
                    String saveFileName = scanner.nextLine();
                    addressBookSystem.saveAddressBookToFile(saveAddressBookName, saveFileName);
                    break;

                case 7:
                    System.out.print("Enter the name of the Address Book to load: ");
                    String loadAddressBookName = scanner.nextLine();
                    System.out.print("Enter the file name to load the Address Book from: ");
                    String loadFileName = scanner.nextLine();
                    addressBookSystem.loadAddressBookFromFile(loadAddressBookName, loadFileName);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
