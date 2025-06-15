package com.skrg.sekoraga.util.filter;

public class LongFilter extends Filter<Long> {
    @Override
    public LongFilter copy() {
        LongFilter newFilter = new LongFilter();
        newFilter.setEquals(this.getEquals());
        newFilter.setNotEquals(this.getNotEquals());
        newFilter.setSpecified(this.getSpecified());
        newFilter.setIn(this.getIn());
        newFilter.setNotIn(this.getNotIn());
        return newFilter;
    }
}
