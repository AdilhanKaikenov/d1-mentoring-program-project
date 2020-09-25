package com.epam.mentoring.memory.task4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kaikenov Adilhan
 **/
public abstract class Animal {

    private static final Logger log = LoggerFactory.getLogger(Animal.class);

    public void play() {
        log.debug("play");
    }

    public void voice() {
        log.debug("voice");
    }

}
