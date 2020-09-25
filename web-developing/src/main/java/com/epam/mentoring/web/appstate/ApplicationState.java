package com.epam.mentoring.web.appstate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kaikenov Adilhan
 **/
public final class ApplicationState {

    private static ApplicationState instance;
    private static long increment;

    private static Map<Long, String> namesRepositoryMap;

    private ApplicationState() {
    }

    public static ApplicationState getInstance() {
        if (instance == null) {
            instance = new ApplicationState();
            namesRepositoryMap = new HashMap<>();
        }
        return instance;
    }

    public void putName(final String name) {
        namesRepositoryMap.put(++increment, name);
    }

    public void putName(final Long id, final String name) {
        namesRepositoryMap.put(id, name);
    }

    public void deleteNameById(final Long id) {
        namesRepositoryMap.remove(id);
    }

    public Map<Long, String> getNamesRepositoryMap() {
        return namesRepositoryMap;
    }

    public void clear() {
        namesRepositoryMap.clear();
    }
}