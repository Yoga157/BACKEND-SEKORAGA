package com.skrg.sekoraga.util.filter;

import java.time.ZonedDateTime;

public class ZonedDateTimeFilter extends Filter<ZonedDateTime> {
    private ZonedDateTime greaterThan;
    private ZonedDateTime lessThan;
    private ZonedDateTime greaterThanOrEqual;
    private ZonedDateTime lessThanOrEqual;

    public ZonedDateTime getGreaterThan() {
        return greaterThan;
    }

    public void setGreaterThan(ZonedDateTime greaterThan) {
        this.greaterThan = greaterThan;
    }

    public ZonedDateTime getLessThan() {
        return lessThan;
    }

    public void setLessThan(ZonedDateTime lessThan) {
        this.lessThan = lessThan;
    }

    public ZonedDateTime getGreaterThanOrEqual() {
        return greaterThanOrEqual;
    }

    public void setGreaterThanOrEqual(ZonedDateTime greaterThanOrEqual) {
        this.greaterThanOrEqual = greaterThanOrEqual;
    }

    public ZonedDateTime getLessThanOrEqual() {
        return lessThanOrEqual;
    }

    public void setLessThanOrEqual(ZonedDateTime lessThanOrEqual) {
        this.lessThanOrEqual = lessThanOrEqual;
    }

    @Override
    public ZonedDateTimeFilter copy() {
        ZonedDateTimeFilter newFilter = new ZonedDateTimeFilter();
        newFilter.setEquals(this.getEquals());
        newFilter.setNotEquals(this.getNotEquals());
        newFilter.setSpecified(this.getSpecified());
        newFilter.setIn(this.getIn());
        newFilter.setNotIn(this.getNotIn());
        newFilter.setGreaterThan(this.getGreaterThan());
        newFilter.setLessThan(this.getLessThan());
        newFilter.setGreaterThanOrEqual(this.greaterThanOrEqual);
        newFilter.setLessThanOrEqual(this.getLessThanOrEqual());
        return newFilter;
    }
}
