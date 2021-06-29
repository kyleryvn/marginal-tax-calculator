package marginaltax;

import marginaltax.businessobject.MarginalTaxService;
import marginaltax.utility.FormatUtility;

import java.util.Scanner;

import static marginaltax.utility.FormatUtility.*;

public class Main {
    // Error message constants
    private static final String NUMBER_ERROR = "ERROR: A number must be entered";
    private static final String NEGATIVE_NUMBER_ERROR = "ERROR: Must be a positive number";
    private static final String FILING_ERROR = "ERROR: Filing status must be either \"S\"/\"MFJ\"/\"MFS\"/\"HH\"";

    // Program variables
    private static boolean isValid = false;
    private static float fedSalary;
    private static String fedFilingStatus;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Input validation for filing status, checks that user enters "S/MFJ/HH"
        System.out.println("Federal Income Tax Calculator");
        System.out.println("(S) Single\n(MFJ) Married Filing Jointly\n(MFS) Married Filing Separately\n(HH) Head of Household");
        do {
            System.out.print("Enter filing status (S/MFJ/MFS/HH) : ");
            try {
                fedFilingStatus = input.nextLine();
                if (!(fedFilingStatus.equalsIgnoreCase("S") || fedFilingStatus.equalsIgnoreCase("MFJ") ||
                        fedFilingStatus.equalsIgnoreCase("MFS") || fedFilingStatus.equalsIgnoreCase("HH")))
                    System.out.println(FILING_ERROR);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!isValid(fedFilingStatus));

        // Input validation for fedSalary, checks user input for type float and fedSalary >= 0
        do {
            System.out.print("Enter taxable federal income : ");
            try {
                if (fedSalary >=0) {
                    fedSalary = Float.parseFloat(input.nextLine());
                    isValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println(NUMBER_ERROR);
            }
        } while (!isValid);

        // Convert user-inputted fedSalary to type double for formatting
        double convertedSalary = convertSalary(fedSalary);

        // Calculate taxes due and convert to type double for formatting
        double taxesDue = Double.parseDouble(Float.toString(MarginalTaxService.getTaxPaid(fedFilingStatus, fedSalary)));

        // Format user-input filing status
        fedFilingStatus = convertFilingStatus(fedFilingStatus);

        // Print results
        System.out.println("\nResults:\nGross Annual Salary : " + FormatUtility.customFormat("$###,###,###.00", convertedSalary) +
                "\nFiled As : " + fedFilingStatus + "\nTaxes Due : " + FormatUtility.customFormat("$###,###,###.00", taxesDue));
    }

    private static boolean isValid(String input) {
        if (input.equalsIgnoreCase("S"))
            return true;
        else if (input.equalsIgnoreCase("MFJ"))
            return true;
        else if (input.equalsIgnoreCase("MFS"))
            return true;
        else if (input.equalsIgnoreCase("HH"))
            return true;
        else
            return false;
    }
}