package com.fx;

import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Person {

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
    
    //Approach 1: Create Methods That Search for Members That Match One Characteristic
    public static void printPersonsOlderThan(List<Person> roster, int age) {
        for (Person p : roster) {
            if (p.getAge() >= age) {
                p.printPerson();
            }
        }
    }

    //Approach 2: Create More Generalized Search Methods
    public static void printPersonsWithinAgeRange(
    	    List<Person> roster, int low, int high) {
    	    for (Person p : roster) {
    	        if (low <= p.getAge() && p.getAge() < high) {
    	            p.printPerson();
    	        }
    	    }
    	}
    
    //Approach 3: Specify Search Criteria Code in a Local Class
    public static void printPersons(
    	    List<Person> roster, CheckPerson tester) {
    	    for (Person p : roster) {
    	        if (tester.test(p)) {
    	            p.printPerson();
    	        }
    	    }
    	}
    
    //Approach 7: Use Lambda Expressions Throughout Your Application
    public static void printPersonsWithPredicate(
    	    List<Person> roster, Predicate<Person> tester) {
    	    for (Person p : roster) {
    	        if (tester.test(p)) {
    	            p.printPerson();
    	        }
    	    }
    	}
    
    //Approach 8: Use Generics More Extensively
    public static <X, Y> void processElements(
    	    Iterable<X> source,
    	    Predicate<X> tester,
    	    Function <X, Y> mapper,
    	    Consumer<Y> block) {
    	    for (X p : source) {
    	        if (tester.test(p)) {
    	            Y data = mapper.apply(p);
    	            block.accept(data);
    	        }
    	    }
    	}
    
    
}

