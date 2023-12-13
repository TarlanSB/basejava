package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class OrganizationSection extends AbstractSection {
    private final List<Organization> companies;

    public OrganizationSection(List<Organization> companies) {
        Objects.requireNonNull(companies, "organizations must not be null");
        this.companies = companies;
    }

    public List<Organization> getCompanies() {
        return companies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return Objects.equals(companies, that.companies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companies);
    }

    @Override
    public String toString() {
        return companies.toString();
    }
}
