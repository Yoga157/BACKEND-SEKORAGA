package com.skrg.sekoraga.domain.criteria;

import java.io.Serializable;
import java.util.Objects;

import com.skrg.sekoraga.util.filter.Criteria;
import com.skrg.sekoraga.util.filter.LongFilter;
import com.skrg.sekoraga.util.filter.StringFilter;

public class AdUserCriteria implements Serializable, Criteria {

    private LongFilter id;

    private StringFilter username;

    private StringFilter email;

    public AdUserCriteria() {

    }

    public AdUserCriteria(AdUserCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.username = other.username == null ? null : other.username.copy();
        this.email = other.email == null ? null : other.email.copy();
    }

    @Override
    public AdUserCriteria copy() {
        return new AdUserCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getUsername() {
        return username;
    }

    public void setUsername(StringFilter username) {
        this.username = username;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AdUserCriteria that = (AdUserCriteria) o;
        return Objects.equals(id, that.id)
                && Objects.equals(username, that.username)
                && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id, username, email);
    }

    @Override
    public String toString() {
        return "AdUserCriteria {" +
                (id != null ? "id=" + id + ", " : "") +
                (username != null ? "username=" + username + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                "}";
    }

}
