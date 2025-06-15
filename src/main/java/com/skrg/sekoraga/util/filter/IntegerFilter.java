package com.skrg.sekoraga.util.filter;

public class IntegerFilter extends Filter<Integer> {
    private Integer greaterThan;
    private Integer lessThan;

    public Integer getGreaterThan() {
        return greaterThan;
    }

    public void setGreaterThan(Integer greaterThan) {
        this.greaterThan = greaterThan;
    }

    public Integer getLessThan() {
        return lessThan;
    }

    public void setLessThan(Integer lessThan) {
        this.lessThan = lessThan;
    }

    @Override
    public IntegerFilter copy() {
        IntegerFilter newFilter = new IntegerFilter();
        newFilter.setEquals(this.getEquals());
        newFilter.setNotEquals(this.getNotEquals());
        newFilter.setSpecified(this.getSpecified());
        newFilter.setIn(this.getIn());
        newFilter.setNotIn(this.getNotIn());
        newFilter.setGreaterThan(this.getGreaterThan());
        newFilter.setLessThan(this.getLessThan());
        return newFilter;
    }
}
