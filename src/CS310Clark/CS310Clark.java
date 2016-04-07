/* Nonprofit Accounting Tax Deductible Donation Report Generator
 * 
 * This program reads data from a file, and it implements a hashmap to store
 * donor and donation data. The email address and check numbers are validated.
 * Another file is read to generate a report for donors telling them whether
 * their requested donations were tax deductible.
 * 
 * Test cases: 
 *
 * Test 1: filepath- 'input/assn5input1.txt' - test working program + report 
 *         donorInput filepath- 'input/donorRequests.txt'  
 * Test 2: filepath- 'input/assn5input5.txt'- test invalid check number  
 *         donorInput filepath- 'input/donorRequests.txt'
 * Test 3: filepath- 'input/assn5input6.txt'- test invalid email address
 *         donorInput filepath- 'input/donorRequests.txt'
 * Test 4: filepath- 'input/assn5input7.txt'- test donor ID not unique
 *         donorInput filepath- 'input/donorRequests.txt' 
 * Test 5: filepath- 'input/assn5input8.txt'- test donation ID not unique
 *         donorInput filepath- 'input/donorRequests.txt'
 * Test 6: filepath- 'input/assn5input9.txt'- test donor not found for delete
 *         donorInput filepath- 'input/donorRequests.txt'
 * Test 7: filepath- 'input/assn5input10.txt'- test donation not found for delete
 *         donorInput filepath- 'input/donorRequests.txt'
 * Test 8: filepath- 'input/assn5input1.txt'- test donor request input file
 *         donorInput  filepath- 'input/DNE.txt'
 * Test 9: filepath- 'input/DNE.txt'- test donor/donation input file
 *         donorInput filepath- 'input/donorRequests.txt'
*/

package CS310Clark;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;

/**
 *
 * @author jeannaclark
 * @version assn5v1
 */
public class CS310Clark {

    // define input files
    final static String INPUT_FILENAME = "input/assn5input1.txt"; 
    final static String DONOR_REQUEST_FILENAME = "input/donorRequests.txt";
    final static String OUTPUT_FILENAME = "output/assn5report.txt";
    
    // instantiate objects
    static DonorImpl donorImpl = new DonorImpl();
    static DonationImpl donationImpl = new DonationImpl();
    static PrintImpl printImpl = new PrintImpl();
    
    /**
     *
     * @param args from command line
     */
    public static void main(String[] args) {
        // read and process data + generate report
        processFile(); 
        donationImpl.cleanUp();
        donorImpl.cleanUp(donationImpl);
        processDonorRequests();
    }
    
    /**
     *
     * @param inputLineValues string[] of line values from the input file
     */
    public static void processDonorAddition(String[] inputLineValues) {        
        Donor donorData = new Donor();
        donorData.setDonorID(Integer.parseInt(inputLineValues[2]));
        donorData.setFirstName(inputLineValues[3]);
        donorData.setLastName(inputLineValues[4]);
        donorData.setEmailAddress(inputLineValues[5]);
        donorData.setPhoneNumber(inputLineValues[6]);

        // email address validation now occurring in DonorImpl().cleanUp()
        
        // check if donor Id is unique
        boolean validId = donorImpl.isIdUnique(Integer.parseInt(inputLineValues[2]));
        if (validId == true) {
            donorImpl.add(donorData, generateHashCode(Integer
                    .parseInt(inputLineValues[2])));
        }
        else {
            System.out.print("\nError - Donor ID " + inputLineValues[2] + 
                    " is not unique.\n\t" 
                + donorData.toString() + "\n\twill NOT be added to list.");
        }
    }
    
    /**
     *
     * @param inputLineValues string[] of line values from the input file
     */
    public static void processDonationAddition(String[] inputLineValues) {
        Donation donationData = new Donation();
        boolean taxDeductible = false;
        
        // set Donation object
        donationData.setDonationID(Integer.parseInt(inputLineValues[2]));
        donationData.setDonorID(Integer.parseInt(inputLineValues[3]));
        
        // trim input to maximum string of 25 characters
        String string25 = inputLineValues[4].substring(0, Math.min(25, 
                inputLineValues[4].length()));
        donationData.setDescription(string25);
        
        donationData.setAmount(Double.parseDouble(inputLineValues[5]));
        donationData.setDate(inputLineValues[6]);

        // convert Y/N input to boolean true or false
        if (inputLineValues[7].equalsIgnoreCase("Y")) {
            taxDeductible = Boolean.valueOf(true);
        }
        else if (inputLineValues[7].equalsIgnoreCase("N")) {
            taxDeductible = Boolean.valueOf(false);
        }
        
        donationData.setTaxDeductible(taxDeductible);
        donationData.setCheckNumber(Integer.parseInt(inputLineValues[8]));
        
        // check number validation method now in DonationImpl.cleanUp()
        
        // check if donor & donation Ids are unique
        boolean validDonorId = donorImpl.isIdUnique(Integer.parseInt(
                inputLineValues[3]));
        boolean validDonationId = donationImpl.isIdUnique(Integer.parseInt
        (inputLineValues[2]));
        if ((validDonorId == false) && (validDonationId == true)) {
            donationImpl.add(donationData, generateHashCode(Integer.parseInt(
                    inputLineValues[2])));
        }
        else {
            System.out.print("\nError - Donation ID not unique and / or donor "
                    + "ID unique.\n\t" + donationData.toString() + "\n\twill NOT "
                    + "be added to list.");
        }
    }
    
    /**
     *
     * @param inputLineValues string[] of line values from the input file
     */
    public static void processDonorDeletion(String[] inputLineValues) {
        // check if donor Id is unique
        boolean validId = donorImpl.isIdUnique(Integer.parseInt(inputLineValues[2]));
        if (validId == false) { // if donor Id is on the list, then:
            boolean donorRemoved = donorImpl.remove(Integer.parseInt(
                    inputLineValues[2]), generateHashCode(Integer.parseInt(
                    inputLineValues[2])));
            if (donationImpl.numberOfDonations(Integer.parseInt(inputLineValues[2])) 
                    > 0) {
                boolean donationRemoved = donationImpl.remove(Integer.parseInt(
                    inputLineValues[2]), generateHashCode(Integer.parseInt(
                    inputLineValues[2])));
                if (donorRemoved == true && donationRemoved == true) {
                    System.out.print("\nDonor " + inputLineValues[2] +
                            "'s donation and donor data has been removed.");
                }
            }
        }
        else {
            System.out.print("\nError - donor " + inputLineValues[2] + " was"
                            + " not found.");
        }
    }
    
    /**
     *
     * @param inputLineValues string[] of line values from the input file
     */
    public static void processDonationDeletion(String[] inputLineValues) {
        // check if donation Id is unique
        boolean validDonationId = donationImpl.isIdUnique(Integer.parseInt(
                inputLineValues[2]));
        if (validDonationId == false) { // if donation ID is in the list
            boolean donationRemoved = donationImpl.remove(Integer.parseInt(
                    inputLineValues[3]), generateHashCode(Integer.parseInt(
                    inputLineValues[2])));
            if (donationRemoved == true) {
                System.out.print("\nDonation " + inputLineValues[2] + " has been "
                        + "removed.");
            }
        }
        else {
            System.out.print("\nError - donation " + inputLineValues[2] 
                        + " was not found.");
        }
    }
    
    // method processes the input file
    private static void processFile() {
        System.out.print("Reading data from file " + INPUT_FILENAME);
        File inFile = new File(INPUT_FILENAME);
        Scanner scanFile = null;
        int lineCount = 0;
        try {
            scanFile = new Scanner(inFile);
        }
        // alert user that the designated file couldn't be opened
        catch (FileNotFoundException fnfe) {
            System.err.println("\nInput file not found: " + INPUT_FILENAME + 
                    ":\n\n" + fnfe);
            System.exit(1);
        }
        
        // parse input file data
        while (scanFile.hasNext()) {
            String lineData = scanFile.nextLine();
            String[] inputLineValues = lineData.split(",");
            
        // index 0 shows donor / donation; index 1 shows add or del
            if ((inputLineValues[0].equalsIgnoreCase("donation")) && 
                    (inputLineValues[1].equalsIgnoreCase("add"))) {
                processDonationAddition(inputLineValues);
            }
            if ((inputLineValues[0].equalsIgnoreCase("donor")) && 
                    (inputLineValues[1].equalsIgnoreCase("add"))) {
                processDonorAddition(inputLineValues);
            }
            if ((inputLineValues[0].equalsIgnoreCase("donation")) && 
                    (inputLineValues[1].equalsIgnoreCase("del"))) {
                processDonationDeletion(inputLineValues);
            }
            if ((inputLineValues[0].equalsIgnoreCase("donor")) && 
                    (inputLineValues[1].equalsIgnoreCase("del"))) {
                processDonorDeletion(inputLineValues);
            } lineCount++;
        }
        
        // close the file
        scanFile.close();
        System.out.print("\nDone reading file. " + lineCount + " lines read");
    }
    
    /**
     * method to process the donor requests file
     */
    public static void processDonorRequests() {
        System.out.print("\n\nReading data from file " + DONOR_REQUEST_FILENAME);
        File inFile = new File(DONOR_REQUEST_FILENAME);
        Scanner scanFile = null;
        PrintWriter fileOut = null; 
        int lineCount = 0;
        try {
            scanFile = new Scanner(inFile);
        }
        // alert user that the designated file couldn't be opened
        catch (FileNotFoundException fnfe) {
            System.err.println("\nInput file not found: " + DONOR_REQUEST_FILENAME + 
                    ":\n\n" + fnfe);
            System.exit(1);
        }
        try {
            File outputFile = new File(OUTPUT_FILENAME);
            fileOut = new PrintWriter(outputFile);
        }
           // throw file input exception if file isn't found 
        catch (IOException ioe) {
            System.err.println("Failed to open file: '" + OUTPUT_FILENAME + 
                    "'. A report cannot be generated.");
        }
        
        // parse input file data
        while (scanFile.hasNext()) {
            String lineData = scanFile.nextLine();
            String[] inputLineValues = lineData.split(" ");
            
        // generate report for parsed data input
            fileOut = printImpl.generateReport(donationImpl, donorImpl, 
              inputLineValues, fileOut);
            lineCount++;
        }
        // close the output file
        fileOut.close();
        
        // close the input file
        scanFile.close();
        System.out.print("\nDone reading file. " + lineCount + " lines read\n");
    }
    
    /**
     *
     * @param id donor or donation id 
     * @return returns the hashcode value (sum of the ascii values of each digit in
     * id reduced by modulus 23)
     */
    public static int generateHashCode(int id) {
        int sum = 0;
        for (int i = 0; i < Integer.toString(id).length(); i++) {
            sum += Integer.toString(id).codePointAt(i);
        } return sum % 23;
    } 
}