package marginaltax;
import marginaltax.service.MarginalTaxService;
import marginaltax.utility.FormatUtility;
import java.util.Scanner;

public class Main {
    private static final String numberError = "ERROR: A number must be entered";
    private static final String filingError = "ERROR: Filing status must be either \"S\" | \"MFJ\" | \"MFS\" | \"HH\"";
    private static boolean isValid = false;
    private static float salary;
    private static String filingStatus;

    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Input validation for filing status, checks that user enters "S/MFJ/HH"
        System.out.println("(S) Single\n(MFJ) Married Filing Jointly\n(MFS) Married Filing Separately\n(HH) Head of Household");
        do {
            System.out.print("Enter filing status (S/MFJ/MFS/HH) : ");
            try {
                filingStatus = input.nextLine();
                if (!(filingStatus.equalsIgnoreCase("S") || filingStatus.equalsIgnoreCase("MFJ") ||
                        filingStatus.equalsIgnoreCase("MFS") || filingStatus.equalsIgnoreCase("HH")))
                    System.out.println(filingError);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!isValid(filingStatus));

        // Input validation for salary, checks user input for type float
        do {
            System.out.print("Enter gross annual salary : ");
            try {
                salary = Float.parseFloat(input.nextLine());
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println(numberError);
            }
        } while (!isValid);

        // Convert user-inputted salary to type double for formatting
        double convertedSalary = Double.parseDouble(Float.toString(salary));

        // Calculate taxes due and convert to type double for formatting
        double taxesDue = Double.parseDouble(Float.toString(MarginalTaxService.getTaxPaid(filingStatus, salary)));

        // Format user-inputted filing status
        filingStatus = convertFilingStatus(filingStatus);

        // Print results
        System.out.println("\nResults:\nGross Annual Salary : " + FormatUtility.customFormat("$###,###.###", convertedSalary) +
                "\nFiled As : " + filingStatus + "\nTaxes Due : " + FormatUtility.customFormat("$###,###.###", taxesDue));
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

    private static String convertFilingStatus(String filingStatus) {
        if (filingStatus.equalsIgnoreCase("S"))
            return "Single";
        else if (filingStatus.equalsIgnoreCase("MFJ"))
            return "Married Filing Jointly";
        else if (filingStatus.equalsIgnoreCase("MFS"))
            return "Married Filing Separately";
        else if (filingStatus.equalsIgnoreCase("HH"))
            return "Head of Household";
        else
            return null;
    }
}