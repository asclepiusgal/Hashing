/* Nonprofit Accounting Tax Deductible Donation Report Generator
 * 
 * This program reads data from a file, and it implements a hashmap to store
 * donor and donation data. The email address and check numbers are validated.
 * Another file is read to generate a report for donors telling them whether
 * their requested donations were tax deductible.
 */

package CS310Clark;

import java.io.PrintWriter;
import java.util.Iterator;

/**
 *
 * @author jeannaclark
 */
public class PrintImpl {
    
    // generate report on donor request

    /**
     *
     * @param donationImpl the donationImpl instance
     * @param donorImpl the donorImpl instance
     * @param lineData the lineData from the donor request file
     * @param fileOut the print writer object
     * @return returns the fileOut object
     */
    public PrintWriter generateReport(DonationImpl donationImpl, DonorImpl 
            donorImpl, String[] lineData, PrintWriter fileOut) {
        
        for (int i = 0; i < donorImpl.MAXSIZE; i++) {
            if (donorImpl.donorHashSet[i] != null ) {
                    if (donorImpl.donorHashSet[i].getDonorID() == Integer
                            .parseInt(lineData[0])) {
                        fileOut.println("Donor " + lineData[0] + ", " + donorImpl
                                .donorHashSet[i].getFirstName() + " " + donorImpl
                                        .donorHashSet[i].getLastName());

                    }
                }
        }
        
            // process report
        for (int j = 1; j < lineData.length; j++) {
            boolean found = false;
            while (found != true) {
                for (int i = 0; i < donationImpl.MAX_SIZE; i++) {
                    if (donationImpl.mapEntry[i].getTopReference() != null) {
                        Iterator<Donation> nodeIterator = donationImpl.mapEntry[i].getTopReference().iterator();
                        while (nodeIterator.hasNext()) {
                            Donation donationData = nodeIterator.next();
                            if (donationData.getDonationID() == Integer
                                    .parseInt(lineData[j])) {
                                fileOut.print("\tDonation " + donationData
                                        .getDonationID() + " for $");
                                fileOut.printf("%8.2f is ", donationData.getAmount());
                                if (donationData.isTaxDeductible() == false) {
                                    fileOut.print("NOT "); // print not tax deductible
                                }
                                fileOut.print("tax deductible\n");
                                found = true;
                                continue;
                            }
                        }
                    }
                }
            }
        } 
return fileOut;
    }
}
    