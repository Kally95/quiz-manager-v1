package com.example.quizmanagerv1.utils;

public class Capitalise {

    public static String capitaliseData(String str) {
        str = str.toLowerCase();
        String newStr = str.substring(0, 1).toUpperCase() + str.substring(1);
        return newStr;
    }
}
