package com.skrg.sekoraga.util.filter;

import java.time.LocalTime;

public class LocalTimeFilter extends Filter<LocalTime> {
    private LocalTime greaterThanOrEqual;
    private LocalTime lessThanOrEqual;

    public LocalTime getGreaterThanOrEqual() {
        return greaterThanOrEqual;
    }

    public void setGreaterThanOrEqual(LocalTime greaterThanOrEqual) {
        this.greaterThanOrEqual = greaterThanOrEqual;
    }

    public LocalTime getLessThanOrEqual() {
        return lessThanOrEqual;
    }

    public void setLessThanOrEqual(LocalTime lessThanOrEqual) {
        this.lessThanOrEqual = lessThanOrEqual;
    }

    @Override
    public LocalTimeFilter copy() {
        LocalTimeFilter newFilter = new LocalTimeFilter();
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
