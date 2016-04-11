/* Nonprofit Accounting Tax Deductible Donation Report Generator
 * 
 * This program reads data from a file, and it implements a hashmap to store
 * donor and donation data. The email address and check numbers are validated.
 * Another file is read to generate a report for donors telling them whether
 * their requested donations were tax deductible.
 */

package CS310Clark;

import java.util.LinkedList;

/**
 *
 * @author jeannaclark
 */
public class MapEntry {
 
    private int key; // hashcode after modulus
    private LinkedList<Donation> topReference;// reference to front of linked list

    /**
     * constructor
     */
    public MapEntry() {
    }

    /**
     *
     * @param key the hashcode key
     * @param topReference the reference to the linked list
     */
    public MapEntry(int key, LinkedList<Donation> topReference) {
        this.key = key;
        this.topReference = topReference;
    }

    /**
     *
     * @return returns the key
     */
    public int getKey() {
        return key;
    }

    /**
     *
     * @param key the hashcode value
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     *
     * @return returns the reference node
     */
    public LinkedList<Donation> getTopReference() {
        return topReference;
    }

    /**
     *
     * @param topReference the linked list top reference
     */
    public void setTopReference(LinkedList<Donation> topReference) {
        this.topReference = topReference;
    }
}