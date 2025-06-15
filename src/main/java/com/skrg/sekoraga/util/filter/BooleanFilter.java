package com.skrg.sekoraga.util.filter;

public class BooleanFilter extends Filter<Boolean> {
    @Override
    public BooleanFilter copy() {
        BooleanFilter newFilter = new BooleanFilter();
        newFilter.setEquals(this.getEquals());
        newFilter.setNotEquals(this.getNotEquals());
        newFilter.setSpecified(this.getSpecified());
        newFilter.setIn(this.getIn());
        newFilter.setNotIn(this.getNotIn());
        return newFilter;
    }
}
