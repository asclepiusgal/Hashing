/* Nonprofit Accounting Tax Deductible Donation Report Generator
 * 
 * This program reads data from a file, and it implements a hashmap to store
 * donor and donation data. The email address and check numbers are validated.
 * Another file is read to generate a report for donors telling them whether
 * their requested donations were tax deductible.
 */

package CS310Clark;

import java.util.Objects;

/**
 *
 * @author jeannaclark
 */
public class Donor {
    // define donor variables
    private int donorID; // unique
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;

    /**
     * empty constructor method
     */
    public Donor() {
    }
    
    /**
     *
     * @param donorID the donor ID data from the file input
     * @param firstName the donor first name data from the file input
     * @param lastName the donor last name data from the file input
     * @param phoneNumber the donor phone number data from the file input
     * @param emailAddress the donor email address data from the file input
     */
    public Donor(int donorID, String firstName, String lastName, String phoneNumber, 
            String emailAddress) {
        this.donorID = donorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    /**
     *
     * @return the Donor objects donor ID
     */
    public int getDonorID() {
        return this.donorID;
    }

    /**
     *
     * @return the Donor objects first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     *
     * @return the Donor objects last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     *
     * @return the Donor objects phone number
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     *
     * @return the Donor objects email address
     */
    public String getEmailAddress() {
        return this.emailAddress;
    }

    /**
     *
     * @param donorID the Donor objects donor ID
     */
    public void setDonorID(int donorID) {
        this.donorID = donorID;
    }

    /**
     *
     * @param firstName the Donor object first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @param lastName the Donor object last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @param phoneNumber the Donor object phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @param emailAddress the Donor object email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Donor other = (Donor) obj;
        if (this.donorID != other.donorID) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        // for test 3a to be accurate, this would need to test whether this equals
        // email address due to how the rubric wanted test 1a hardcoded; same is
        // true for the next equals() statement. current setup causes donor to 
        // always be not equal
        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
            return false;
        }
        if (!Objects.equals(this.emailAddress, other.emailAddress)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Donor{" + "donorID=" + donorID + ", firstName=" + firstName 
                + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber 
                + ", emailAddress=" + emailAddress + '}';
    }
    
    /**
     *
     * @param emailAddress the email address from the Donor object
     * @return boolean value of true means valid email address
     */
    public Boolean validateEmailAddress(String emailAddress) {
        if (emailAddress.contains("@") == false) {
            return false;
        }
        return true;
    }
}