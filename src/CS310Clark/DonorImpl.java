/* Nonprofit Accounting Tax Deductible Donation Report Generator
 * 
 * This program reads data from a file, and it implements a hashmap to store
 * donor and donation data. The email address and check numbers are validated.
 * Another file is read to generate a report for donors telling them whether
 * their requested donations were tax deductible.
 */

package CS310Clark;

import java.util.Iterator;

/**
 *
 * @author jeannaclark
 */
public class DonorImpl {
    
    // initialize variables

    /**
     * initialize variables
     */
    public int MAXSIZE;
    private int currSize;
    private int numIdx;

    /**
     * initialize variables
     */
    public Donor[] donorHashSet;
    
    // constructor

    /**
     * constructor
     */
    public DonorImpl() {
        MAXSIZE = 23;
        currSize = 0;
        donorHashSet = new Donor[MAXSIZE];
        for (int idx = 0; idx < donorHashSet.length; idx++) {
            donorHashSet[idx] = null;
        }
    }
    
    /**
     *
     * @param donor donor object
     * @param hashCode hashcode from donor id
     */
    public void add(Donor donor, int hashCode) {
        boolean found = false;
        while (!found) {
            if (donorHashSet[hashCode] == null) {
                donorHashSet[hashCode] = donor;
                currSize++;
                found = true;
            }
            else {
                hashCode++;
                if (hashCode > donorHashSet.length) {
                    hashCode = 0;
                }
            }
        }
    }
    
    /**
     *
     * @param donorID donor id
     * @param hashCode hashcode from donor id
     * @return returns true if donor removed
     */
    public boolean remove(int donorID, int hashCode) {
        boolean found = false;
        while (!found) {
            if (donorHashSet[hashCode].getDonorID() == donorID) {
                donorHashSet[hashCode] = null;
                currSize--;
                found = true;
            }
            else {
                hashCode++;
                if (hashCode > donorHashSet.length) {
                    hashCode = 0;
                }
            }
        } return found; // found = true when donor removed 
    }
    
    /**
     *
     * @param id donor ID
     * @return returns false if not unique
     */
    public boolean isIdUnique(int id) {
        boolean valid = true; // returns true if ID unique
        for (int idx = 0; idx < donorHashSet.length; idx++) {
            if (donorHashSet[idx] != null) {
                if (donorHashSet[idx].getDonorID() == id) {
                    valid = false;
                }
            }
        } return valid;
    }
    
    /**
     *
     * @param donationImpl donationImpl instance
     */
    public void cleanUp(DonationImpl donationImpl) {
        // check for invalid email address
        
        for (int idx = 0; idx < donorHashSet.length; idx++) {
            if (donorHashSet[idx] != null) {
                boolean validEmail = donorHashSet[idx].validateEmailAddress
        (donorHashSet[idx].getEmailAddress());
                int donorId = donorHashSet[idx].getDonorID();
                if (validEmail == false) {
                    donorHashSet[idx] = null; // remove donor
                    System.out.print("\nDonor " + donorId + " removed for invalid "
                            + "email.");
                    
                    for (int i = 0; i < MAXSIZE && donationImpl.mapEntry[i].getTopReference()
                            != null; i++) {
                        Iterator<Donation> nodeIterator = donationImpl
                                .mapEntry[i].getTopReference().iterator();
                        while (nodeIterator.hasNext() && nodeIterator != null) {
                            Donation donation = nodeIterator.next();
                            if (donation.getDonorID() == donorId) {
                                nodeIterator.remove();
                                System.out.print("\nDonation " + 
                                        donation.getDonationID() + " removed for "
                                        + "invalid check number of " + donation
                                                .getCheckNumber() + ".\n");
                            } 
                        }
                    }
                }
            }
        }
    }
}