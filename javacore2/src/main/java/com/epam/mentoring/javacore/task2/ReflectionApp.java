package com.epam.mentoring.javacore.task2;

import com.epam.mentoring.javacore.task1.model.Apartment;
import com.epam.mentoring.javacore.task1.model.Dimension;
import com.epam.mentoring.javacore.task1.model.appliance.HomeAppliance;
import com.epam.mentoring.javacore.task1.model.appliance.impl.Washer;
import com.epam.mentoring.javacore.task1.model.enums.ApplianceCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @author Kaikenov Adilhan
 **/
public class ReflectionApp {

    private static final Logger log = LoggerFactory.getLogger(ReflectionApp.class);

    public static void main(String[] args) throws Exception{

//        1.Use reflection to instantiate all objects in client code via constructors (with or without arguments)

        final String className = "com.epam.mentoring.javacore.task1.model.Apartment";

        // 1st approach - dynamic loading (without arguments)
        Class<?> apartmentClass1 = Class.forName(className);
        Object newInstance = apartmentClass1.newInstance();
        if (newInstance instanceof Apartment) {
            Apartment apartment1 = (Apartment) newInstance;
            log.info(apartment1.toString());
        }

        // 2d approach (without arguments)
        Class<Apartment> apartmentClass2 = Apartment.class;
        Apartment apartment2 = apartmentClass2.newInstance();

        log.info(apartment2.toString());

        // 3d approach - via constructor (with arguments)
        Class[] constructorParamTypes = {String.class, double.class, List.class};
        final String address = "Street 38/3";
        final double area = 68.5;
        Object[] arguments = {address, area, new ArrayList<>()};
        Constructor<Apartment> constructor = apartmentClass2.getConstructor(constructorParamTypes);
        Apartment apartment3 = constructor.newInstance(arguments);

        log.info(apartment3.toString());

//        2.Use reflection API to fill private fields without setters

        final int powerConsumption = 400;
        final int maxLinenWeight = 3;

        Washer washer = new Washer();
        Class<? extends Washer> washerClass = washer.getClass(); // Washer extends HomeAppliance
        Class<?> washerClassSuperclass = washerClass.getSuperclass(); // HomeAppliance class

        // Private fields can be changed. To do this, we need to get java.lang.reflect.Field type object
        // using the getDeclaredField() method,  call the setAccessible(true) method
        // and use the set() method to set the field value.
        Field nameField = washerClassSuperclass.getDeclaredField("name");
        Field dimensionField = washerClassSuperclass.getDeclaredField("dimension");
        Field categoryField = washerClassSuperclass.getDeclaredField("category");
        Field powerConsumptionField = washerClassSuperclass.getDeclaredField("powerConsumption");
        Field maxLinenWeightField = washerClass.getDeclaredField("maxLinenWeight");

        nameField.setAccessible(true);
        dimensionField.setAccessible(true);
        categoryField.setAccessible(true);
        powerConsumptionField.setAccessible(true);
        maxLinenWeightField.setAccessible(true);

        nameField.set(washer, "Washer");
        dimensionField.set(washer, new Dimension(10, 10, 10));
        categoryField.set(washer, ApplianceCategory.KITCHEN_APPLIANCE);
        powerConsumptionField.set(washer, powerConsumption);
        maxLinenWeightField.set(washer, maxLinenWeight);

        log.info("Use reflection API to fill private fields without setters:\n {}", washer);

//        3.Use reflection API to change method calls from direct call on reflection call in client code

        // call method without argument
        Method onMethod = washerClass.getMethod("on");
        onMethod.invoke(washer);

        // call method with argument
        Method setCategoryMethod = washerClass.getMethod("setCategory", ApplianceCategory.class);
        setCategoryMethod.invoke(washer, ApplianceCategory.HOME_APPLIANCE);

        // call method with return value
        Method getCategoryMethod = washerClass.getMethod("getCategory");
        Object category = getCategoryMethod.invoke(washer);

        log.info("Category {}", category);

//        4.Print the metadata for class in human-readable format format
//          (about all fields, methods, modifiers and etc.)
        ReflectionApp.printMetaData(washerClass);

    }

    private static void printMetaData(Class<? extends HomeAppliance> aClass) {
        Class superclass = aClass.getSuperclass();
        System.out.println("Name: " + aClass.getSimpleName());
        System.out.println("Modifier: " + Modifier.toString(aClass.getModifiers()));
        System.out.println("Extends class: " + superclass.getSimpleName());
        System.out.println("Implements interfaces: " + Arrays.toString(aClass.getInterfaces()));
        System.out.println("Fields: ");

        Set<Field> fields = new HashSet<>(Arrays.asList(superclass.getFields()));
        fields.addAll(Arrays.asList(superclass.getDeclaredFields()));
        fields.addAll(Arrays.asList(aClass.getFields()));
        fields.addAll(Arrays.asList(aClass.getDeclaredFields()));

        for (final Field field : fields) {
            String fieldStr = String.format("\t%s %s %s",
                    Modifier.toString(field.getModifiers()),
                    field.getType().getSimpleName(),
                    field.getName());
            System.out.println(fieldStr);
        }

        System.out.println("Methods: ");

        Set<Method> methods = new HashSet<>(Arrays.asList(aClass.getMethods()));
        methods.addAll(Arrays.asList(aClass.getDeclaredMethods()));

        for (final Method method : methods) {
            String methodStr = String.format("\t%s %s %s ( %s ) throws %s",
                    Modifier.toString(method.getModifiers()),
                    method.getReturnType().getSimpleName(),
                    method.getName(),
                    Arrays.toString(method.getParameterTypes()),
                    Arrays.toString(method.getExceptionTypes()));
            System.out.println(methodStr);
        }
    }

}
