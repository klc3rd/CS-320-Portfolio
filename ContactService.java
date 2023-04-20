/**
 * Author: Kenneth Cluck
 * CS-320-T4208
 * Project One
 */

package dev.kensworkshop.projectone;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactService {
    HashMap<String, Contact> contacts = new HashMap<>();

    // Get individual contact
    public Contact getContact(String contactID) {
        Contact contact = contacts.get(contactID);

        if (contact == null) {
            throw new RuntimeException("Contact not found");
        }

        // If contactID is valid, delete contact
        return contact;
    }

    // Insert a contact into hashmap
    public void insertContact(Contact contact) {
        if (contacts.get(contact.ID) != null) {
            throw new RuntimeException("ID must be a unique value");
        }

        contacts.put(contact.ID, contact);
        System.out.println("Contact added");
    }

    // Delete contact from hashmap
    public void deleteContact(String contactID) {
        if (contacts.get(contactID) == null) {
            throw new RuntimeException("Contact not found");
        }

        // If contactID is valid, delete contact
        contacts.remove(contactID);
        System.out.println("Contact deleted");
    }

    // Update firstName
    public void updateFirstName(String contactID, String firstName) {
        Contact currentContact = contacts.get(contactID);

        if (currentContact == null) {
            throw new RuntimeException("Contact not found");
        }

        currentContact.setFirstName(firstName);
        System.out.println("Contact updated");
    }

    // Update lastName
    public void updateLastName(String contactID, String lastName) {
        Contact currentContact = contacts.get(contactID);

        if (currentContact == null) {
            throw new RuntimeException("Contact not found");
        }

        currentContact.setLastName(lastName);
        System.out.println("Contact updated");
    }

    // Update phone
    public void updatePhone(String contactID, String phone) {
        Contact currentContact = contacts.get(contactID);

        if (currentContact == null) {
            throw new RuntimeException("Contact not found");
        }

        currentContact.setPhone(phone);
        System.out.println("Contact updated");
    }

    // Update address
    public void updateAddress(String contactID, String address) {
        Contact currentContact = contacts.get(contactID);

        if (currentContact == null) {
            throw new RuntimeException("Contact not found");
        }

        currentContact.setAddress(address);
        System.out.println("Contact updated");
    }
}
