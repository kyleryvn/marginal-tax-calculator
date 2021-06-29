package com.github.kyleryvn.marginaltax.utility;

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
        return myFormatter.format(value);
    }
    
}