package com.epam.mentoring.spring.intro.model;

import java.util.List;

public class Company {

    private List<Position> positions;

    public List<Position> getPositions() {
        return this.positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public void destroy() {
        this.positions.clear();
    }
}
