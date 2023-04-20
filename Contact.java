/**
 * Author: Kenneth Cluck
 * CS-320-T4208
 * Project One
 */

package dev.kensworkshop.projectone;

public class Contact {
    public static final String INVALID_LENGTH_10 = "Field must be at least 10 characters";
    public static final String INVALID_LENGTH_30 = "Field must be at least 30 characters";
    public static final String INVALID_PHONE = "Phone number must be 10 digits";

    public String ID;
    public String firstName;
    public String lastName;
    public String phone;
    public String address;

    public Contact(String ID, String firstName, String lastName, String phone, String address) {
        if (ID.length() > 10 || ID.length() == 0) {
            throw new RuntimeException(INVALID_LENGTH_10);
        }

        if(firstName.length() > 10 || firstName.length() == 0) {
            throw new RuntimeException(INVALID_LENGTH_10);
        }

        if (lastName.length() > 10 || lastName.length() == 0) {
            throw new RuntimeException(INVALID_LENGTH_10);
        }

        if (isPhoneInvalid(phone)) {
            throw new RuntimeException(INVALID_PHONE);
        }

        if (address.length() > 30 || address.length() == 0) {
            throw new RuntimeException(INVALID_LENGTH_30);
        }

        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public String getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName.length() > 10 || firstName.length() == 0) {
            throw new RuntimeException(INVALID_LENGTH_10);
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.length() > 10 || lastName.length() == 0) {
            throw new RuntimeException(INVALID_LENGTH_10);
        }
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (isPhoneInvalid(phone)) {
            throw new RuntimeException(INVALID_PHONE);
        }
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.length() > 30 || address.length() == 0) {
            throw new RuntimeException(INVALID_LENGTH_30);
        }
        this.address = address;
    }

    // Validate if phone is all digits
    private boolean isPhoneInvalid(String number) {
        if(number.length() != 10) {
            return true;
        }

        try {
            Long.parseLong(number);
        } catch(RuntimeException exception) {
            return true;
        }

        return false;
    }
}
