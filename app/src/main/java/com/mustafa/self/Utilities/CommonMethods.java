package com.mustafa.self.Utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Youssif Hamdy on 6/1/2020.
 */
public class CommonMethods {



    public static double ParseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            } catch(Exception e) {
                return 0;
            }
        }
        else return 0;
    }


    public static String round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return String.valueOf(bd.doubleValue());

    }


    public static String round(String x, int places) {
        if (places < 0) throw new IllegalArgumentException();
        double value = ParseDouble(x);
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return String.valueOf(bd.doubleValue());

    }


}
