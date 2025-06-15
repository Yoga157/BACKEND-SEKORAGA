package com.skrg.sekoraga.util.filter;

public class FloatFilter extends Filter<Float> {
    @Override
    public FloatFilter copy() {
        FloatFilter newFilter = new FloatFilter();
        newFilter.setEquals(this.getEquals());
        newFilter.setNotEquals(this.getNotEquals());
        newFilter.setSpecified(this.getSpecified());
        newFilter.setIn(this.getIn());
        newFilter.setNotIn(this.getNotIn());
        return newFilter;
    }
}
