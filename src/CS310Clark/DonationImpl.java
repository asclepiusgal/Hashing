/* Nonprofit Accounting Tax Deductible Donation Report Generator
 * 
 * This program reads data from a file, and it implements a hashmap to store
 * donor and donation data. The email address and check numbers are validated.
 * Another file is read to generate a report for donors telling them whether
 * their requested donations were tax deductible.
 */

package CS310Clark;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author jeannaclark
 */
public class DonationImpl implements MapEntry {
    // define variables
    private int donationID; // unique value
    private int donorID; // unique & match donor.java donorID
    private String description; // < 25 char in length
    private Double amount;
    private String date;
    private boolean taxDeductible;
    private int checkNumber;

    /**
     * variables defined
     */
    public int MAX_SIZE = 23;
    LinkedList<Donation>[] donationList = new LinkedList[MAX_SIZE];
    
    /**
     *
     * @param obj donation object
     * @param hashCode hashcode from the donation id
     * @return returns true if added
     */
    public boolean add(Donation obj, int hashCode) {
        if (donationList[hashCode] == null) {
            donationList[hashCode] = new LinkedList();
        }
        donationList[hashCode].add(obj);
        return true;
    }
   
    /**
     *
     * @param donorId donor id
     * @param hashCode hashcode from the donation id
     * @return false if didn't remove
     */
    public boolean remove(int donorId, int hashCode) {
        Iterator<Donation> nodeIterator = donationList[hashCode].iterator();
        while (nodeIterator.hasNext()) {
            Donation donationData = nodeIterator.next();
            if (donationData.getDonorID() == donorId) {
                nodeIterator.remove();
                return true;
            }
        } return false;
    }

    /**
     *
     * @param donorId donor id
     * @param donationId donation id
     * @param hashCode the hashcode from the donation id
     * @return false if id not removed
     */
    public boolean remove(int donorId, int donationId, int hashCode) {
        boolean removed = false;
        Iterator<Donation> nodeIterator = donationList[hashCode].iterator();
        while (nodeIterator.hasNext()) {
            Donation donationData = nodeIterator.next();
            if (donationData.getDonationID() == donationId && 
                    donationData.getDonorID() == donorId) {
                nodeIterator.remove();
                removed = true;
            }
        } return removed;
    }

    // returns whether the donation ID is unique

    /**
     *
     * @param donationId donation id
     * @return true if id is unique
     */
    public boolean isIdUnique(int donationId) {  
        for (int i = 0; i < MAX_SIZE; i++) {
            if (donationList[i] != null) {
                Iterator<Donation> nodeIterator = donationList[i].iterator();
                while (nodeIterator.hasNext()) {
                if (nodeIterator.next().getDonationID() == donationId) {
                    return false;
                    }
                } 
            }
        } return true;
    }

    /**
     *
     * @param index index of array to return
     * @param hashCode hashcode value of from donation id
     * @return returns the donation object
     */
    public Donation getDonation(int index, int hashCode) {
        return donationList[hashCode].get(index);  
    }

    // returns the number of donations by donor ID

    /**
     *
     * @param donorId donor ID
     * @return returns number of donations
     */
    public int numberOfDonations(int donorId) {
        int numDonations = 0;
        for (int i = 0; i < MAX_SIZE && donationList[i] != null; i++) {
            Iterator<Donation> nodeIterator = donationList[i].iterator();
            while (nodeIterator.hasNext()) {
            if (nodeIterator.next().getDonorID() == donorId) {
                numDonations++;
                }
            } 
        } return numDonations;
    }

    /**
     *
     * @param donorId donor ID
     * @param hashCode donation id used to calculate
     * @return returns number of donations
     */
    public int numberOfDonations(int donorId, int hashCode) {
        Iterator<Donation> nodeIterator = donationList[hashCode].iterator();
        int count = 0;
        while (nodeIterator.hasNext()) {
            if (nodeIterator.next().getDonorID() == donorId) {
                count++;
            }
        } return count;
    }

    /**
     * 
     */
    public void cleanUp() {
        // validate check number
        for (int i = 0; i < MAX_SIZE; i++) {
            if (donationList[i] != null) {
                Iterator<Donation> nodeIterator = donationList[i].iterator();
                while (nodeIterator.hasNext() && nodeIterator != null) {
                    Donation donation = nodeIterator.next();
                    boolean validCheckNumber = donation.validateCheckNumber
                                (donation.getCheckNumber());
                    if (validCheckNumber == false) {
                        nodeIterator.remove();
                        System.out.print("\nDonation " + donation.getDonationID() + 
                                " removed for invalid check number of " + 
                                donation.getCheckNumber() + ".\n");
                    } 
                }
            }
        }
    }
}
