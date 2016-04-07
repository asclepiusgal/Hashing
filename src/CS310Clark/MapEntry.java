/* Nonprofit Accounting Tax Deductible Donation Report Generator
 * 
 * This program reads data from a file, and it implements a hashmap to store
 * donor and donation data. The email address and check numbers are validated.
 * Another file is read to generate a report for donors telling them whether
 * their requested donations were tax deductible.
 */

package CS310Clark;

/**
 *
 * @author jeannaclark
 */
public interface MapEntry {
    
    /**
     *
     * @param obj donation object 
     * @param hashCode the donation hashCode value
     * @return boolean value true if added
     */
    boolean add(Donation obj, int hashCode);

    /**
     *
     * @param donorId the donor ID
     * @param donationId the donation ID
     * @param hashCode the HashCode calculated from the donation id
     * @return boolean value true id removed
     */
    boolean remove(int donorId, int donationId, int hashCode);

    /**
     *
     * @param donationId the donation id
     * @return boolean value true if id unique
     */
    boolean isIdUnique(int donationId);

    /**
     *
     * @param index index of array to return
     * @param hashCode hashcode from the donation id value
     * @return donation object
     */
    Donation getDonation(int index, int hashCode);

    /**
     *
     * @param hashCode hashcode from the donation id value
     * @return returns number of donations
     */
    int numberOfDonations(int hashCode);

    /**
     *
     * @param donorId donor ID
     * @param hashCode hashcode for the donation id value
     * @return returns number of donations
     */
    int numberOfDonations(int donorId, int hashCode);

    /**
     * 
     */
    void cleanUp();
}