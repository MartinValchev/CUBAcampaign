package com.company.campaign.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NamePattern("%s %s|name,website")
@Table(name = "CAMPAIGN_BANNER_POSITION")
@Entity(name = "campaign$BannerPosition")
public class BannerPosition extends StandardEntity {
    private static final long serialVersionUID = 409295628593982484L;

    @Column(name = "NAME", nullable = false, length = 50)
    protected String name;

    @Column(name = "IMPRESSIONS_LIMIT", nullable = false)
    protected Long impressionsLimit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WEBSITE_ID")
    protected Website website;

    public void setWebsite(Website website) {
        this.website = website;
    }

    public Website getWebsite() {
        return website;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setImpressionsLimit(Long impressionsLimit) {
        this.impressionsLimit = impressionsLimit;
    }

    public Long getImpressionsLimit() {
        return impressionsLimit;
    }


}