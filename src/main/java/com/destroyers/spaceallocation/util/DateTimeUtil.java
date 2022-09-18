package com.destroyers.spaceallocation.util;

import java.time.LocalTime;

public class DateTimeUtil {

    public static Boolean afterOrEqual(LocalTime localTime1, LocalTime localTime2){
        return localTime1.equals(localTime2) || localTime1.isAfter(localTime2);
    }

    public static Boolean beforeOrEqual(LocalTime localTime1, LocalTime localTime2){
        return localTime1.equals(localTime2) || localTime1.isBefore(localTime2);
    }
}
