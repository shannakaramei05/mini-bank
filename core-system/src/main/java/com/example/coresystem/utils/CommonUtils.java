package com.example.coresystem.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.util.Date;
import java.util.Random;
import java.util.logging.SimpleFormatter;

public class CommonUtils {

    private static final Random random = new Random();

    // Function to generate a 12-digit account number
    public static String generateAccountNumber() {
        String prefix = "1211"; // Fixed prefix
        String suffix = generateNumber(8);
        return  prefix + suffix;
    }

    // Function to generate a 16-digit card number with the first 4 digits fixed as 1211
    public static String generateCardNumber() {
        String prefix = "4620"; // Fixed prefix
        String suffix = generateNumber(12); // Random 12-digit suffix
        return prefix + suffix;
    }

    // Utility function to generate a number with a specified length
    private static String generateNumber(int length) {
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < length; i++) {
            number.append(random.nextInt(10)); // Append a random digit (0-9)
        }
        return number.toString();
    }

    public static String currDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.now().format(formatter);
    }
    public static String currTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        return LocalDateTime.now().format(formatter);
    }

    //set as refno
    public static String yyyyMMddHHmmss() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return LocalDateTime.now().format(formatter);
    }

    public static String curDateAddYear(int year) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.now().plusYears(year).format(formatter);
    }
}
