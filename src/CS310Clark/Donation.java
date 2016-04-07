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
public class Donation {
    
    // define variables
    private int donationID; // unique value
    private int donorID; // unique & match donor.java donorID
    private String description; // < 25 char in length
    private Double amount;
    private String date;
    private boolean taxDeductible;
    private int checkNumber;

    /**
     * constructor empty constructor
     */
    public Donation() {
    }

    /**
     *
     * @param donationID donation ID data from file
     * @param donorID donation donor ID data from file
     * @param description donation description data from file
     * @param amount donation amount data from file
     * @param date donation date data from file
     * @param taxDeductible donation tax deductible status data from file
     * @param checkNumber donation check number data from file
     */
    public Donation(int donationID, int donorID, String description, 
            Double amount, String date, boolean taxDeductible, int checkNumber) {
        this.donationID = donationID;
        this.donorID = donorID;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.taxDeductible = taxDeductible;
        this.checkNumber = checkNumber;
    }

    /**
     *
     * @return Donation object donation ID data
     */
    public int getDonationID() {
        return this.donationID;
    }

    /**
     *
     * @return donation object donor ID data
     */
    public int getDonorID() {
        return this.donorID;
    }

    /**
     *
     * @return donation object description data
     */
    public String getDescription() {
        return this.description;
    }

    /**
     *
     * @return donation object amount data
     */
    public Double getAmount() {
        return this.amount;
    }

    /**
     *
     * @return donation object date data
     */
    public String getDate() {
        return this.date;
    }

    /**
     *
     * @return donation object tax deductible status data
     */
    public boolean isTaxDeductible() {
        return this.taxDeductible;
    }

    /**
     *
     * @return donation object check number data
     */
    public int getCheckNumber() {
        return this.checkNumber;
    }

    /**
     *
     * @param donationID donation object donation ID data
     */
    public void setDonationID(int donationID) {
        this.donationID = donationID;
    }

    /**
     *
     * @param donorID donation object donor ID data
     */
    public void setDonorID(int donorID) {
        this.donorID = donorID;
    }

    /**
     *
     * @param description donation object description data
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @param amount donation object amount data
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     *
     * @param date donation object date data
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @param taxDeductible donation object check number data
     */
    public void setTaxDeductible(boolean taxDeductible) {
        this.taxDeductible = taxDeductible;
    }

    /**
     *
     * @param checkNumber donation object check number data
     */
    public void setCheckNumber(int checkNumber) {
        this.checkNumber = checkNumber;
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
        final Donation other = (Donation) obj;
        if (this.donationID != other.donationID) {
            return false;
        }
        if (this.donorID != other.donorID) {
            return false;
        }
        if (this.taxDeductible != other.taxDeductible) {
            return false;
        }
        if (this.checkNumber != other.checkNumber) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Donation{" + "donationID=" + donationID + ", donorID=" 
                + donorID + ", description=" + description + ", amount=" 
                + amount + ", date=" + date + ", taxDeductible=" + taxDeductible 
                + ", checkNumber=" + checkNumber + '}';
    }
    
    /**
     *
     * @param checkNumber donation object check number data
     * @return true means the check number is valid
     */
    public Boolean validateCheckNumber(int checkNumber) {
        if ((checkNumber >= 5000) || (checkNumber <= 100)) {
            return false;
        }
        return true;
    }
}