package com.skrg.sekoraga.util.filter;

import java.time.LocalDate;

public class LocalDateFilter extends Filter<LocalDate> {
    private LocalDate greaterThanOrEqual;
    private LocalDate lessThanOrEqual;

    public LocalDate getGreaterThanOrEqual() {
        return greaterThanOrEqual;
    }

    public void setGreaterThanOrEqual(LocalDate greaterThanOrEqual) {
        this.greaterThanOrEqual = greaterThanOrEqual;
    }

    public LocalDate getLessThanOrEqual() {
        return lessThanOrEqual;
    }

    public void setLessThanOrEqual(LocalDate lessThanOrEqual) {
        this.lessThanOrEqual = lessThanOrEqual;
    }

    @Override
    public LocalDateFilter copy() {
        LocalDateFilter newFilter = new LocalDateFilter();
        newFilter.setEquals(this.getEquals());
        newFilter.setNotEquals(this.getNotEquals());
        newFilter.setSpecified(this.getSpecified());
        newFilter.setIn(this.getIn());
        newFilter.setNotIn(this.getNotIn());
        newFilter.setGreaterThanOrEqual(this.getGreaterThanOrEqual());
        newFilter.setLessThanOrEqual(this.getLessThanOrEqual());
        return newFilter;
    }
}
