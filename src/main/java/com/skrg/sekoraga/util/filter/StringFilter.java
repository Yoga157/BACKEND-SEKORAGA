package com.skrg.sekoraga.util.filter;

public class StringFilter extends Filter<String> {
    @Override
    public StringFilter copy() {
        StringFilter newFilter = new StringFilter();
        newFilter.setEquals(this.getEquals());
        newFilter.setNotEquals(this.getNotEquals());
        newFilter.setSpecified(this.getSpecified());
        newFilter.setIn(this.getIn());
        newFilter.setNotIn(this.getNotIn());
        newFilter.setContains(this.getContains());
        newFilter.setDoesNotContain(this.getDoesNotContain());
        return newFilter;
    }
}
