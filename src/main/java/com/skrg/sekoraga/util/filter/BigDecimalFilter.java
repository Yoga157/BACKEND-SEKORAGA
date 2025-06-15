package com.skrg.sekoraga.util.filter;

import java.math.BigDecimal;

public class BigDecimalFilter extends Filter<BigDecimal> {
    private BigDecimal greaterThan;
    private BigDecimal lessThan;

    public BigDecimal getGreaterThan() {
        return greaterThan;
    }

    public void setGreaterThan(BigDecimal greaterThan) {
        this.greaterThan = greaterThan;
    }

    public BigDecimal getLessThan() {
        return lessThan;
    }

    public void setLessThan(BigDecimal lessThan) {
        this.lessThan = lessThan;
    }

    @Override
    public BigDecimalFilter copy() {
        BigDecimalFilter newFilter = new BigDecimalFilter();
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
