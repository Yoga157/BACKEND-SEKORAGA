package com.skrg.sekoraga.util.filter;

import java.util.Objects;

public class RangeFilter<T extends Comparable<? super T>> extends Filter<T> {

    private T greaterThan;
    private T lessThan;
    private T greaterThanOrEqual;
    private T lessThanOrEqual;

    public T getGreaterThan() {
        return greaterThan;
    }

    public void setGreaterThan(T greaterThan) {
        this.greaterThan = greaterThan;
    }

    public T getLessThan() {
        return lessThan;
    }

    public void setLessThan(T lessThan) {
        this.lessThan = lessThan;
    }

    public T getGreaterThanOrEqual() {
        return greaterThanOrEqual;
    }

    public void setGreaterThanOrEqual(T greaterThanOrEqual) {
        this.greaterThanOrEqual = greaterThanOrEqual;
    }

    public T getLessThanOrEqual() {
        return lessThanOrEqual;
    }

    public void setLessThanOrEqual(T lessThanOrEqual) {
        this.lessThanOrEqual = lessThanOrEqual;
    }

    @Override
    public RangeFilter<T> copy() {
        RangeFilter<T> newFilter = new RangeFilter<>();
        newFilter.setEquals(this.getEquals());
        newFilter.setNotEquals(this.getNotEquals());
        newFilter.setSpecified(this.getSpecified());
        newFilter.setIn(this.getIn());
        newFilter.setNotIn(this.getNotIn());
        newFilter.setGreaterThan(this.getGreaterThan());
        newFilter.setLessThan(this.getLessThan());
        newFilter.setGreaterThanOrEqual(this.getGreaterThanOrEqual());
        newFilter.setLessThanOrEqual(this.getLessThanOrEqual());
        return newFilter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof RangeFilter))
            return false;
        if (!super.equals(o))
            return false;
        RangeFilter<?> that = (RangeFilter<?>) o;
        return Objects.equals(greaterThan, that.greaterThan) &&
                Objects.equals(lessThan, that.lessThan) &&
                Objects.equals(greaterThanOrEqual, that.greaterThanOrEqual) &&
                Objects.equals(lessThanOrEqual, that.lessThanOrEqual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), greaterThan, lessThan, greaterThanOrEqual, lessThanOrEqual);
    }

    @Override
    public String toString() {
        return "RangeFilter{" +
                "greaterThan=" + greaterThan +
                ", lessThan=" + lessThan +
                ", greaterThanOrEqual=" + greaterThanOrEqual +
                ", lessThanOrEqual=" + lessThanOrEqual +
                ", equals=" + getEquals() +
                ", notEquals=" + getNotEquals() +
                ", specified=" + getSpecified() +
                ", in=" + getIn() +
                ", notIn=" + getNotIn() +
                '}';
    }
}
