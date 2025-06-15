package com.skrg.sekoraga.util.filter;

public class DoubleFilter extends Filter<Double> {
    @Override
    public DoubleFilter copy() {
        DoubleFilter newFilter = new DoubleFilter();
        newFilter.setEquals(this.getEquals());
        newFilter.setNotEquals(this.getNotEquals());
        newFilter.setSpecified(this.getSpecified());
        newFilter.setIn(this.getIn());
        newFilter.setNotIn(this.getNotIn());
        return newFilter;
    }
}
