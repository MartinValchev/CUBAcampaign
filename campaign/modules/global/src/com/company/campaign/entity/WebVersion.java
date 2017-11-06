package com.company.campaign.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum WebVersion implements EnumClass<Integer> {

    desktop(10),
    mobile(20);

    private Integer id;

    WebVersion(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static WebVersion fromId(Integer id) {
        for (WebVersion at : WebVersion.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}