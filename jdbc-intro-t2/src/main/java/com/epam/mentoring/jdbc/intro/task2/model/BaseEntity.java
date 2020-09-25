package com.epam.mentoring.jdbc.intro.task2.model;

/**
 * @author Kaikenov Adilhan
 */
public abstract class BaseEntity {

    private Integer id;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BaseEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        return this.id != null ? this.id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return this.id != null ? this.id.hashCode() : 0;
    }
}
