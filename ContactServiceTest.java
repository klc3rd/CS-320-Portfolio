/**
 * Author: Kenneth Cluck
 * CS-320-T4208
 * Project One
 */

package dev.kensworkshop.projectone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ContactServiceTest {
    // Valid contact values
    String ID = "abc";
    String firstName = "FirstName";
    String lastName = "LastName";
    String phone = "1234567890";
    String address = "123 North Test Ln";

    // Secondary contact values
    String firstName2 = "FirstName2";
    String lastName2 = "LastName2";
    String phone2 = "9876543210";
    String address2 = "321 South Test Ln";

    // Invalid contact values
    String invalidFirstName = "InvalidLongFirstName";
    String invalidLastName = "InvalidLongLastName";
    String invalidLongPhone = "123456789012345";
    String invalidShortPhone = "123";
    String invalidPhone = "abcdefghij";
    String invalidAddress = "1234567890 Long Address Field Value";

    ContactService contactService;

    @BeforeEach
    void setupContactService() {
        contactService = new ContactService();
    }

    // Test if adding a contact works
    @Test
    @DisplayName("Insert a contact")
    void testInsertContact() {
        Contact contact = new Contact(ID, firstName, lastName, phone, address);
        assertDoesNotThrow(() -> contactService.insertContact(contact), "Unable to add contact");

        assertAll(() -> {
            Contact insertedContact = contactService.getContact(ID);

            assertEquals(insertedContact.getID(), ID, "ID does not match");
            assertEquals(insertedContact.getFirstName(), firstName, "firstName does not match");
            assertEquals(insertedContact.getLastName(), lastName, "lastName does not match");
            assertEquals(insertedContact.getPhone(), phone, "phone does not match");
            assertEquals(insertedContact.getAddress(), address, "address does not match");
        });
    }

    // Test that the service does not allow a duplicate contact ID
    @Test
    @DisplayName("Insert duplicate contact")
    void testDuplicateContact() {
        Contact contact = new Contact(ID, firstName, lastName, phone, address);
        Contact contact2 = new Contact(ID, firstName2, lastName2, phone2, address2);

        assertDoesNotThrow(() -> contactService.insertContact(contact), "Unable to add contact");
        assertThrows(RuntimeException.class, () -> contactService.insertContact(contact2),
                "Added duplicate contact");
    }

    // Test that a contact can be deleted
    @Test
    @DisplayName("Delete contact")
    void testDeleteContact() {
        Contact contact = new Contact(ID, firstName, lastName, phone, address);

        assertDoesNotThrow(() -> contactService.insertContact(contact), "Unable to add contact");
        assertDoesNotThrow(() -> contactService.deleteContact(ID),
                "Added duplicate contact");

        assertThrows(RuntimeException.class, () -> contactService.getContact(ID),
                "Contact was not deleted");

        assertThrows(RuntimeException.class, () -> contactService.deleteContact(ID),
                "Deleting already removed contact did not throw error");
    }

    // Test contact updating
    @Test
    @DisplayName("Update contact values")
    void testUpdateContact() {
        Contact contact = new Contact(ID, firstName, lastName, phone, address);
        assertDoesNotThrow(() -> contactService.insertContact(contact), "Unable to add contact");

        Contact retrievedContact = contactService.getContact(ID);

        // Update each value and check for errors
        assertAll(() -> {
            assertDoesNotThrow(() -> contactService.updateFirstName(ID, firstName2),
                    "Could not update firstName");
            assertDoesNotThrow(() -> contactService.updateLastName(ID, lastName2),
                    "Could not update lastName");
            assertDoesNotThrow(() -> contactService.updatePhone(ID, phone2),
                    "Could not update phone");
            assertDoesNotThrow(() -> contactService.updateAddress(ID, address2),
                    "Could not update address");
        });

        // Update each value with null and make sure it throws an error
        assertAll(() -> {
            assertThrows(RuntimeException.class, () -> contactService.updateFirstName(ID, null),
                    "Updated firstName with null");
            assertThrows(RuntimeException.class, () -> contactService.updateLastName(ID, null),
                    "Updated lastName with null");
            assertThrows(RuntimeException.class, () -> contactService.updatePhone(ID, null),
                    "Updated phone with null");
            assertThrows(RuntimeException.class, () -> contactService.updateAddress(ID, null),
                    "Updated address with null");
        });

        // Update each value with an invalid value and make sure it throws an error
        assertAll(() -> {
            assertThrows(RuntimeException.class, () -> contactService.updateFirstName(ID, invalidFirstName),
                    "Updated firstName with invalid value");
            assertThrows(RuntimeException.class, () -> contactService.updateLastName(ID, invalidLastName),
                    "Updated lastName with invalid value");
            assertThrows(RuntimeException.class, () -> contactService.updatePhone(ID, invalidLongPhone),
                    "Updated phone with invalid long value");
            assertThrows(RuntimeException.class, () -> contactService.updatePhone(ID, invalidShortPhone),
                    "Updated phone with invalid short value");
            assertThrows(RuntimeException.class, () -> contactService.updatePhone(ID, invalidPhone),
                    "Updated phone with invalid value");
            assertThrows(RuntimeException.class, () -> contactService.updateAddress(ID, invalidAddress),
                    "Updated address with invalid value");
        });

        // Validate updated information
        assertAll(() -> {
            assertEquals(retrievedContact.getFirstName(), firstName2, "firstName not updated");
            assertEquals(retrievedContact.getLastName(), lastName2, "lastName not updated");
            assertEquals(retrievedContact.getPhone(), phone2, "phone not updated");
            assertEquals(retrievedContact.getAddress(), address2, "address not updated");
        });
    }
}
