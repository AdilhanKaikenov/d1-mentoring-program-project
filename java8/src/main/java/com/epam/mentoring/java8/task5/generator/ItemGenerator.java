package com.epam.mentoring.java8.task5.generator;

import com.epam.mentoring.java8.task5.A;

import java.util.Random;

/**
 * @author Kaikenov Adilhan
 **/
public final class ItemGenerator {

    public static A generateItem() {
        final Random random = new Random();

        final String[] words = {"accept", "apologize", "battle", "break", "carry", "communicate", "dance",
                "enjoy", "organise", "billion", "eighteen", "fourteen", "morning", "number", "seventeen",
                "thousand", "tomorrow", "yesterday", "cheek", "breast", "neck", "tongue", "wrist", "stomach"
        };
        final int max = 999;
        final int min = 99;
        final int randomNum = random.nextInt((max - min) + 1) + min;

        return new A(randomNum, words[random.nextInt(words.length)]);
    }
}
