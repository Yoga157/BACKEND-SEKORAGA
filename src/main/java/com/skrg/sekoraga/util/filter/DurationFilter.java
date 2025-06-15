package com.skrg.sekoraga.util.filter;

import java.time.Duration;

public class DurationFilter extends Filter<Duration> {
    @Override
    public DurationFilter copy() {
        DurationFilter newFilter = new DurationFilter();
        newFilter.setEquals(this.getEquals());
        newFilter.setNotEquals(this.getNotEquals());
        newFilter.setSpecified(this.getSpecified());
        newFilter.setIn(this.getIn());
        newFilter.setNotIn(this.getNotIn());
        return newFilter;
    }
}
