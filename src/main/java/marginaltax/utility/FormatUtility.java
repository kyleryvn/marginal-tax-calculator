package marginaltax.utility;

import java.text.DecimalFormat;

public class FormatUtility {

    public static String customFormat(String pattern, double value) {
        /*
        Examples
        customFormat("###,###.###", 123456.789);
        customFormat("###.##", 123456.789);
        customFormat("000000.000", 123.78);
        customFormat("$###,###.###", 12345.67);
        */

        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        return output;
    }

    public static double convertSalary(float number) {
        return Double.parseDouble(Float.toString(number));
    }

    public static String convertFilingStatus(String fedFilingStatus) {
        if (fedFilingStatus.equalsIgnoreCase("S"))
            return "Single";
        else if (fedFilingStatus.equalsIgnoreCase("MFJ"))
            return "Married Filing Jointly";
        else if (fedFilingStatus.equalsIgnoreCase("MFS"))
            return "Married Filing Separately";
        else if (fedFilingStatus.equalsIgnoreCase("HH"))
            return "Head of Household";
        else
            return null;
    }
}