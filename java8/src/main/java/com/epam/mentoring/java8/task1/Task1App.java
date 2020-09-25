package com.epam.mentoring.java8.task1;

import com.epam.mentoring.java8.task1.model.Person;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;



/**
 * @author Kaikenov Adilhan
 **/
public class Task1App {

//    private static final Logger log = LoggerFactory.getLogger(Task3App.class);

    public static void main(String[] args) {

        Person oscar = new Person("Oscar", 35);
        Person freddie = new Person("Freddie", 24);
        Person alfie = new Person("Alfie", 23);
        Person george = new Person("George", 45);
        Person harry = new Person("Harry", 43);
        Person jack = new Person("Jack", 21);
        Person lily = new Person("Lily", 33);
        Person emily = new Person("Emily", 18);
        Person mia = new Person("Mia", 27);
        Person jacob = new Person("Jacob", 36);

        // A collection of Persons
        List<Person> people = new ArrayList<>(Arrays.asList(
                oscar, freddie, alfie, george, harry, jack, lily, emily, mia, jacob
        ));

//      Two instances of Comparator<Person> interface using lambda expressions:
//      first one for comparing by name,
        people.sort((o1, o2) -> o1.getName().compareTo(o2.getName())); // method reference
        //  forEach method for printing information about all the persons.
        people.forEach(person -> {System.out.println(String.format("Name %s", person.getName()));});

//      second one – by age
        people.sort(((o1, o2) -> Integer.compare(o1.getAge(), o2.getAge()))); // method reference
        //  forEach method for printing information about all the persons.
        people.forEach(person -> {System.out.println(String.format("Age %d", person.getAge()));});
    }
}
