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
public class DonationImpl {
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

    /**
     * creates an array of map entry data
     */
    public MapEntry[] mapEntry = new MapEntry[MAX_SIZE]; // hashcode = key; linkedList ref = value

    /**
     * creates a new Donation linked list
     */
    public LinkedList<Donation> donationList = new LinkedList<Donation>();

    /**
     * loads the key values and top list references
     */
    public void generateMapEntry() {
        // initialize the MapEntry keys and top references
        for (int i = 0; i < MAX_SIZE; i++) {
            LinkedList<Donation> donationList = new LinkedList<Donation>();
            mapEntry[i] = new MapEntry();    
            mapEntry[i].setKey(i); // key will be the hashcode
            mapEntry[i].setTopReference(donationList); // value is the linked list reference
        }
    }
        
    /**
     *
     * @param obj donation object
     * @param hashCode hashcode from the donation id
     * @return returns true if added
     */
    public boolean add(Donation obj, int hashCode) {
        mapEntry[hashCode].getTopReference().add(obj);
        return true;
    }
   
    /**
     *
     * @param donorId donor id
     * @param hashCode hashcode from the donation id
     * @return false if didn't remove
     */
    public boolean remove(int donorId, int hashCode) {
        Iterator<Donation> nodeIterator = mapEntry[hashCode].getTopReference().iterator();
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
        Iterator<Donation> nodeIterator = mapEntry[hashCode].getTopReference().iterator();
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
            if (mapEntry[i].getTopReference() != null) {
                            
                Iterator<Donation> nodeIterator = mapEntry[i].getTopReference().iterator();
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
        return mapEntry[hashCode].getTopReference().get(index);  
    }

    // returns the number of donations by donor ID

    /**
     *
     * @param donorId donor ID
     * @return returns number of donations
     */
    public int numberOfDonations(int donorId) {
        int numDonations = 0;
        for (int i = 0; i < MAX_SIZE && mapEntry[i].getTopReference() != null; i++) {
            Iterator<Donation> nodeIterator = mapEntry[i].getTopReference().iterator();
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
        Iterator<Donation> nodeIterator = mapEntry[hashCode].getTopReference().iterator();
        int count = 0;
        while (nodeIterator.hasNext()) {
            if (nodeIterator.next().getDonorID() == donorId) {
                count++;
            }
        } return count;
    }

    /**
     * validates the check number and removes invalid entries
     */
    public void cleanUp() {
        // validate check number
        for (int i = 0; i < MAX_SIZE; i++) {
            if (mapEntry[i].getTopReference() != null) {
                Iterator<Donation> nodeIterator = mapEntry[i].getTopReference().iterator();
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
