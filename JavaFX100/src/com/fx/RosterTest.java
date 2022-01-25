package com.fx;

import java.time.LocalDate;
import java.time.chrono.IsoChronology;

public class RosterTest {

    public enum Sex {
        MALE, FEMALE
    }

    String name;
    LocalDate birthday;
    Sex gender;
    String emailAddress;

    public int getAge() {
        return birthday
        		.until(IsoChronology.INSTANCE.dateNow())
        		.getYears();
    }

    public void printPerson() {
        
    }
}