package com.example.hrs;

import java.text.NumberFormat;
import java.util.Locale;

public class HRSUtils {
    public static String formatCurrency(double number, Locale locale){
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        return format.format(number);
    }
}
