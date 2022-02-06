package com.example.weighttracker;

import java.util.ArrayList;

public class HelperMisc {

    public static boolean isAnyStringNull(String... strings) {
        for (String s: strings) {
            if (s.equals("")) {
                return true;
            }
        }
        return false;
    }


}
