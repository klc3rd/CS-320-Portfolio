/**
 * Author: Kenneth Cluck
 * CS-320-T4208
 * Project One
 */

package dev.kensworkshop.projectone;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static dev.kensworkshop.projectone.Contact.INVALID_LENGTH_10;
import static dev.kensworkshop.projectone.Contact.INVALID_LENGTH_30;
import static dev.kensworkshop.projectone.Contact.INVALID_PHONE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ContactTest {
    // Valid contact values
    String ID = "abc";
    String firstName = "FirstName";
    String lastName = "LastName";
    String phone = "1234567890";
    String address = "123 North Test Ln";
    
    // Valid contact update values
    String firstName2 = "FirstName2";
    String lastName2 = "LastName2";
    String phone2 = "9876543210";
    String address2 = "321 South Test Ln";

    // Invalid contact values
    String invalidID = "InvalidLongID";
    String invalidFirstName = "InvalidLongFirstName";
    String invalidLastName = "InvalidLongLastName";
    String invalidLongPhone = "123456789012345";
    String invalidShortPhone = "123";
    String invalidPhone = "abcdefghij";
    String invalidAddress = "1234567890 Long Address Field Value";

    @Test
    @DisplayName("Create a valid user")
    void createUser() {
        Contact contact = new Contact(ID, firstName, lastName, phone, address);
        assertAll(() -> {
            assertEquals(contact.getID(), ID, "ID does not match");
            assertEquals(contact.getFirstName(), firstName, "firstName does not match");
            assertEquals(contact.getLastName(), lastName, "lastName does not match");
            assertEquals(contact.getPhone(), phone, "phone does not match");
            assertEquals(contact.getAddress(), address, "address does not match");
        });
    }

    // Tests for invalid contact creation
    @Test
    @DisplayName("Invalid contact creation")
    void testInvalidContactCreation() {
        assertAll(() -> {
            Exception invalidLongIDException = assertThrows(RuntimeException.class, () -> {
                Contact contact = new Contact(invalidID, firstName, lastName, phone, address);
            }, "Created contact with invalid ID");

            Exception invalidLongFirstnameException = assertThrows(RuntimeException.class, () -> {
                Contact contact = new Contact(ID, invalidFirstName, lastName, phone, address);
            }, "Created contact with invalid firstName");

            Exception invalidLongLastnameException = assertThrows(RuntimeException.class, () -> {
                Contact contact = new Contact(ID, firstName, invalidLastName, phone, address);
            }, "Created contact with invalid lastName");

            Exception invalidLongAddressException = assertThrows(RuntimeException.class, () -> {
                Contact contact = new Contact(ID, firstName, lastName, phone, invalidAddress);
            }, "Created contact with invalid address");

            // Check errors
            assertAll(() -> {
                assertEquals(INVALID_LENGTH_10, invalidLongIDException.getMessage(), "Long ID error message does not match");
                assertEquals(INVALID_LENGTH_10, invalidLongFirstnameException.getMessage(), "Long firstName error message does not match");
                assertEquals(INVALID_LENGTH_10, invalidLongLastnameException.getMessage(), "Long lastName error message does not match");
                assertEquals(INVALID_LENGTH_30, invalidLongAddressException.getMessage(), "Long address error message does not match");
            });

            // Testing various invalid phone numbers
            assertAll(() -> {
                Exception longPhoneException = assertThrows(RuntimeException.class, () -> {
                    Contact contact = new Contact(ID, firstName, lastName, invalidLongPhone, address);
                },"Created contact with a long phone");

                Exception shortPhoneException = assertThrows(RuntimeException.class, () -> {
                    Contact contact = new Contact(ID, firstName, lastName, invalidShortPhone, address);
                }, "Created contact with a short phone");

                Exception invalidPhoneException = assertThrows(RuntimeException.class, () -> {
                    Contact contact = new Contact(ID, firstName, lastName, invalidPhone, address);
                }, "Created contact with a invalid phone");

                // Check errors
                assertAll(() -> {
                    assertEquals(INVALID_PHONE, longPhoneException.getMessage(), "Long phone error does not match");
                    assertEquals(INVALID_PHONE, shortPhoneException.getMessage(), "Short phone error does not match");
                    assertEquals(INVALID_PHONE, invalidPhoneException.getMessage(), "Invalid phone error does not match");
                });
            });
        });
    }

    @Test
    @DisplayName("Null contact value")
    void testNullContactValue() {
        assertAll(() -> {
            assertThrows(RuntimeException.class, () -> {
                Contact contact = new Contact("", firstName, lastName, phone, address);
            }, "Created contact with null ID");

            assertThrows(RuntimeException.class, () -> {
                Contact contact = new Contact(ID, "", lastName, phone, address);
            }, "Created contact with null firstName");

            assertThrows(RuntimeException.class, () -> {
                Contact contact = new Contact(ID, firstName, "", phone, address);
            }, "Created contact with null lastName");

            assertThrows(RuntimeException.class, () -> {
                Contact contact = new Contact(ID, firstName, lastName, "", address);
            }, "Created contact with a null phone");

            assertThrows(RuntimeException.class, () -> {
                Contact contact = new Contact(ID, firstName, lastName, phone, "");
            }, "Created contact with null address");
        });
    }
    
    // Test for valid updates
    @Test
    @DisplayName("Valid contact updates")
    void testValidUpdates() {
        Contact contact = new Contact(ID, firstName, lastName, phone, address);
        assertAll(() -> {
            assertEquals(contact.getID(), ID, "ID does not match");
            assertEquals(contact.getFirstName(), firstName, "firstName does not match");
            assertEquals(contact.getLastName(), lastName, "lastName does not match");
            assertEquals(contact.getPhone(), phone, "phone does not match");
            assertEquals(contact.getAddress(), address, "address does not match");
        });
        
        assertDoesNotThrow(() -> {
        	contact.setFirstName(firstName2);
        });
        assertDoesNotThrow(() -> {
        	contact.setLastName(lastName2);
        });
        assertDoesNotThrow(() -> {
        	contact.setPhone(phone2);
        });
        assertDoesNotThrow(() -> {
        	contact.setAddress(address2);
        });
        
        assertAll(() -> {
            assertEquals(contact.getFirstName(), firstName2, "firstName was not updated");
            assertEquals(contact.getLastName(), lastName2, "lastName was not updated");
            assertEquals(contact.getPhone(), phone2, "phone was not updated");
            assertEquals(contact.getAddress(), address2, "address was not updated");
        });
        
    }

    // Test for updating invalid information
    @Test
    @DisplayName("Invalid contact updates")
    void testInvalidContactUpdates() {
        Contact contact = new Contact(ID, firstName, lastName, phone, address);

        assertAll(() -> {
            // Test firstname
            Exception invalidLongFirstnameException = assertThrows(RuntimeException.class, () -> {
                contact.setFirstName(invalidFirstName);
            }, "Updated with invalid firstName");

            assertThrows(RuntimeException.class, () -> {
                contact.setFirstName(null);
            }, "Updated with null firstName");

            // Test lastName
            Exception invalidLongLastnameException = assertThrows(RuntimeException.class, () -> {
                contact.setLastName(invalidLastName);
            }, "Updated with invalid lastName");

            assertThrows(RuntimeException.class, () -> {
                contact.setLastName(null);
            }, "Updated with null lastName");

            // Test address
            Exception invalidLongAddressException = assertThrows(RuntimeException.class, () -> {
                contact.setAddress(invalidAddress);
            }, "Updated with invalid address");

            assertThrows(RuntimeException.class, () -> {
                contact.setAddress(null);
            }, "Updated with null address");

            // Check errors
            assertAll(() -> {
                assertEquals(INVALID_LENGTH_10, invalidLongFirstnameException.getMessage(), "Long firstName error message does not match");
                assertEquals(INVALID_LENGTH_10, invalidLongLastnameException.getMessage(), "Long lastName error message does not match");
                assertEquals(INVALID_LENGTH_30, invalidLongAddressException.getMessage(), "Long address error message does not match");
            });

            // Test invalid phone numbers
            assertAll(() -> {
                // Test long phone number
               Exception longPhoneException = assertThrows(RuntimeException.class, () -> {
                    contact.setPhone(invalidLongPhone);
                }, "Updated with invalid long phone number");

                // Test short phone number
                Exception shortPhoneException = assertThrows(RuntimeException.class, () -> {
                    contact.setPhone(invalidShortPhone);
                }, "Updated with invalid short phone number");

                // Test invalid phone number
                Exception invalidPhoneException = assertThrows(RuntimeException.class, () -> {
                    contact.setPhone(invalidPhone);
                }, "Updated with invalid phone number");

                assertThrows(RuntimeException.class, () -> {
                    contact.setPhone(null);
                }, "Updated with null number");

                // Check errors
                assertAll(() -> {
                    assertEquals(INVALID_PHONE, longPhoneException.getMessage(), "Long phone error does not match");
                    assertEquals(INVALID_PHONE, shortPhoneException.getMessage(), "Short phone error does not match");
                    assertEquals(INVALID_PHONE, invalidPhoneException.getMessage(), "Invalid phone error does not match");
                });
            });
        });
    }
}
