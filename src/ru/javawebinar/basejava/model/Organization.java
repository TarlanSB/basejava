package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class Organization {
    private final Link homePage;
    private final List<Period> periods;

    public Organization(String name, String url, List<Period> periods) {
        this.homePage = new Link(name, url);
        this.periods = periods;
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homePage, that.homePage) && Objects.equals(periods, that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, periods);
    }

    @Override
    public String toString() {
        return homePage +",\n" + periods;
    }
}
