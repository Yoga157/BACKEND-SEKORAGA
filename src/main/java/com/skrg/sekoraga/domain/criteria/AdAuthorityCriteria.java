package com.skrg.sekoraga.domain.criteria;

import java.io.Serializable;
import java.util.Objects;

import com.skrg.sekoraga.util.filter.Criteria;
import com.skrg.sekoraga.util.filter.LongFilter;
import com.skrg.sekoraga.util.filter.StringFilter;

public class AdAuthorityCriteria implements Serializable, Criteria {

    private LongFilter id;

    private StringFilter name;

    public AdAuthorityCriteria() {
    }

    public AdAuthorityCriteria(AdAuthorityCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
    }

    @Override
    public Criteria copy() {
        return new AdAuthorityCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AdAuthorityCriteria that = (AdAuthorityCriteria) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id, name);
    }

    @Override
    public String toString() {
        return "AdAuthorityCriteria {" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                "}";
    }

}
