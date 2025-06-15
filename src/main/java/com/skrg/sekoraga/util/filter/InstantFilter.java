package com.skrg.sekoraga.util.filter;

import java.time.Instant;

public class InstantFilter extends Filter<Instant> {
    private Instant greaterThan;
    private Instant greaterThanOrEqual;
    private Instant lessThan;
    private Instant lessThanOrEqual;

    public Instant getGreaterThan() {
        return greaterThan;
    }

    public void setGreaterThan(Instant greaterThan) {
        this.greaterThan = greaterThan;
    }

    public Instant getLessThan() {
        return lessThan;
    }

    public void setLessThan(Instant lessThan) {
        this.lessThan = lessThan;
    }

    public Instant getGreaterThanOrEqual() {
        return greaterThanOrEqual;
    }

    public void setGreaterThanOrEqual(Instant greaterThanOrEqual) {
        this.greaterThanOrEqual = greaterThanOrEqual;
    }

    public Instant getLessThanOrEqual() {
        return lessThanOrEqual;
    }

    public void setLessThanOrEqual(Instant lessThanOrEqual) {
        this.lessThanOrEqual = lessThanOrEqual;
    }

    @Override
    public InstantFilter copy() {
        InstantFilter newFilter = new InstantFilter();
        newFilter.setEquals(this.getEquals());
        newFilter.setNotEquals(this.getNotEquals());
        newFilter.setSpecified(this.getSpecified());
        newFilter.setIn(this.getIn());
        newFilter.setNotIn(this.getNotIn());
        newFilter.setGreaterThan(this.getGreaterThan());
        newFilter.setLessThan(this.getLessThan());
        newFilter.setGreaterThanOrEqual(this.greaterThanOrEqual);
        newFilter.setLessThanOrEqual(this.lessThanOrEqual);
        return newFilter;
    }
}
