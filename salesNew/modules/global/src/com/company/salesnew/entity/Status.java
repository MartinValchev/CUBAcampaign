package com.company.salesnew.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum Status implements EnumClass<Integer> {

    NEW(10),
    VALIDATED(20),
    SHIPPED(30);

    private Integer id;

    Status(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static Status fromId(Integer id) {
        for (Status at : Status.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}