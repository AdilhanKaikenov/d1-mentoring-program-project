package com.epam.mentoring.java8.task2;

import com.epam.mentoring.java8.task1.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author Kaikenov Adilhan
 **/
public class Task2App {

    public static void main(String[] args) {

        //  Implement each of main Java Standard Library functional interfaces (supplier, predicate etc.)
        //  using lambda expressions.
        /*
            Examples:
            - Supplier
            - Predicate
            - Consumer
            - Function
         */
        // Supplier (takes no arguments but it returns some value by calling its get() method.)
        Supplier<Person> personSupplier = Task2App::generatePerson;

        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            people.add(personSupplier.get());
        }

        List<Person> filtered = people.stream()
                .filter(createFilterByName("Jack")) // Predicate (returns boolean)
                .collect(Collectors.toList());

        Consumer<String> print = Task2App::print; // Consumer does the opposite of the Supplier (returns no result)
        filtered.forEach(person -> print.accept(String.format("Name %s", person.getName())));


        Person oscar = new Person("Oscar", 35);
        // Function accepts one argument and produces a result.
        Function<Person, String> personStringFunction = Person::toString;
        functionTest(personStringFunction, oscar);

        // Create your own functional interface and add several implementations:
        // using lambda expressions and
        int lambdaResult = funcInterfaceTest(arg -> 2 * 20);

        // using inner anonymous classes
        int anonResult = funcInterfaceTest(new MyCustomInterface() {
            @Override
            public int apply(final int arg) {
                return 2 * 20;
            }
        });

        MyCustomInterface myCustomInterface = arg -> 0;
        // Add few default methods to it and use them.
        myCustomInterface.info();
        myCustomInterface.show();

        // Add few static methods to it and use them
        boolean result = MyCustomInterface.isNull("String");
    }

    private static Predicate<Person> createFilterByName(final String name) {
        return person -> {

            if (person == null) {
                return false;
            }

            return person.getName().equalsIgnoreCase(name);
        };
    }

    private static void print(final String source) {
        System.out.println(source);
    }

    private static Person generatePerson() {
        final Random random = new Random();

        final String[] names = {"Oscar", "Freddie", "Harry", "Jack", "Lily"};
        final int maxAge = 60;
        final int minAge = 20;
        final int randomAge = random.nextInt((maxAge - minAge) + 1) + minAge;

        return new Person(names[random.nextInt(names.length)], randomAge);
    }

    private static void functionTest(final Function<Person, String> func, final Person person) {
        System.out.println(func.apply(person));
    }

    public static int increase(int num){
        return num + 1;
    }

    private static int funcInterfaceTest(final MyCustomInterface customInterface) {
        return customInterface.apply(1);
    }
}
