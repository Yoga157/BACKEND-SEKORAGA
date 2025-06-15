package com.skrg.sekoraga.util.filter;

import java.util.UUID;

public class UUIDFilter extends Filter<UUID> {
    @Override
    public UUIDFilter copy() {
        UUIDFilter newFilter = new UUIDFilter();
        newFilter.setEquals(this.getEquals());
        newFilter.setNotEquals(this.getNotEquals());
        newFilter.setSpecified(this.getSpecified());
        newFilter.setIn(this.getIn());
        newFilter.setNotIn(this.getNotIn());
        return newFilter;
    }
}
