package com.skrg.sekoraga.util.filter;

import java.util.List;

public class Filter<T> {
    private T equals;
    private T notEquals;
    private Boolean specified;
    private List<T> in;
    private List<T> notIn;
    private T contains;
    private T doesNotContain;

    public Filter() {

    }

    public Filter(Filter<T> filter) {
        if (filter != null) {
            this.equals = filter.equals;
            this.notEquals = filter.notEquals;
            this.specified = filter.specified;
            this.in = filter.in;
            this.notIn = filter.notIn;
            this.contains = filter.contains;
            this.doesNotContain = filter.doesNotContain;
        }
    }

    public T getEquals() {
        return equals;
    }

    public void setEquals(T equals) {
        this.equals = equals;
    }

    public T getNotEquals() {
        return notEquals;
    }

    public void setNotEquals(T notEquals) {
        this.notEquals = notEquals;
    }

    public Boolean getSpecified() {
        return specified;
    }

    public void setSpecified(Boolean specified) {
        this.specified = specified;
    }

    public List<T> getIn() {
        return in;
    }

    public void setIn(List<T> in) {
        this.in = in;
    }

    public List<T> getNotIn() {
        return notIn;
    }

    public void setNotIn(List<T> notIn) {
        this.notIn = notIn;
    }

    public T getContains() {
        return contains;
    }

    public void setContains(T contains) {
        this.contains = contains;
    }

    public T getDoesNotContain() {
        return doesNotContain;
    }

    public void setDoesNotContain(T doesNotContain) {
        this.doesNotContain = doesNotContain;
    }

    public Filter<T> copy() {
        Filter<T> newFilter = new Filter<>();
        newFilter.setEquals(this.equals);
        newFilter.setNotEquals(this.notEquals);
        newFilter.setSpecified(this.specified);
        newFilter.setIn(this.in);
        newFilter.setNotIn(this.notIn);
        newFilter.setContains(this.contains);
        newFilter.setDoesNotContain(this.doesNotContain);
        return newFilter;
    }
}
